package es.ercilla.autenticacion.seguridad;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import es.ercilla.autenticacion.config.InicioAplicacion;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class AutenticacionFiltro implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Cache-Control", "no-store");
        resp.setHeader("X-Content-Type-Options", "nosniff");
        resp.setHeader("X-Frame-Options", "DENY");
        String ruta = req.getRequestURI().substring(req.getContextPath().length());
        if (ruta.equals("/login") || ruta.equals("/favicon.ico")) {
            chain.doFilter(request, response);
            return;
        }
        if (ruta.equals("/")) {
            resp.sendRedirect(req.getContextPath() + "/panel");
            return;
        }
        String token = Cookies.leer(req);
        if (token == null || token.isBlank()) {
            noAutorizado(req, resp);
            return;
        }
        try {
            JwtService jwt = (JwtService) req.getServletContext().getAttribute(InicioAplicacion.JWT);
            Claims claims = jwt.validar(token);
            if (ruta.equals("/admin") && !"ADMIN".equals(claims.get("rol", String.class))) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Solo para ADMIN");
                return;
            }
            // Protección de solicitudes con efectos: token antifalsificación dentro del JWT firmado.
            if (!("GET".equals(req.getMethod()) || "HEAD".equals(req.getMethod()) || "OPTIONS".equals(req.getMethod()))) {
                String esperado = claims.get("csrf", String.class);
                String recibido = req.getParameter("csrf");
                if (recibido == null || !MessageDigest.isEqual(
                        esperado.getBytes(StandardCharsets.UTF_8), recibido.getBytes(StandardCharsets.UTF_8))) {
                    resp.sendError(HttpServletResponse.SC_FORBIDDEN, "CSRF no válido");
                    return;
                }
            }
            req.setAttribute("usuario", claims.getSubject());
            req.setAttribute("rol", claims.get("rol", String.class));
            req.setAttribute("csrf", claims.get("csrf", String.class));
            chain.doFilter(request, response);
        } catch (JwtException | IllegalArgumentException e) {
            Cookies.borrar(req, resp);
            noAutorizado(req, resp);
        }
    }

    private void noAutorizado(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Para un futuro endpoint /api/* devolver 401 en vez de redirección HTML.
        if (req.getRequestURI().substring(req.getContextPath().length()).startsWith("/")) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT ausente o no válido");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
