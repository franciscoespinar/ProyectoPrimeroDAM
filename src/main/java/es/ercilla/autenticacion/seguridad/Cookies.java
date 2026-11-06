package es.ercilla.autenticacion.seguridad;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class Cookies {
    public static final String NOMBRE = "AUTH_TOKEN";
    private Cookies() { }

    public static void guardar(HttpServletRequest req, HttpServletResponse resp, String jwt) {
        escribir(req, resp, jwt, JwtService.DURACION_SEGUNDOS);
    }

    public static void borrar(HttpServletRequest req, HttpServletResponse resp) {
        escribir(req, resp, "", 0);
    }

    private static void escribir(HttpServletRequest req, HttpServletResponse resp, String valor, int segundos) {
        Cookie cookie = new Cookie(NOMBRE, valor);
        cookie.setHttpOnly(true);
        cookie.setSecure(req.isSecure()); // En producción: HTTPS obligatorio
        cookie.setPath(req.getContextPath().isEmpty() ? "/" : req.getContextPath());
        cookie.setMaxAge(segundos);
        cookie.setAttribute("SameSite", "Strict");
        resp.addCookie(cookie);
    }

    public static String leer(HttpServletRequest req) {
        if (req.getCookies() == null) return null;
        for (Cookie cookie : req.getCookies()) {
            if (NOMBRE.equals(cookie.getName())) return cookie.getValue();
        }
        return null;
    }
}
