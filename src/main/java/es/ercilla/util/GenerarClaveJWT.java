package es.ercilla.util;

import java.security.SecureRandom;
import java.util.Base64;

public class GenerarClaveJWT {
	public static void main(String[] args) {
		byte[] clave = new byte[32];
		SecureRandom random = new SecureRandom();
		random.nextBytes(clave);
		String claveBase64 = Base64.getEncoder().encodeToString(clave);
		System.out.println(claveBase64);
		//w37MoIPK5B7ULukMWD/Fa8vekI7yPF/eCbkC2f4gIus=
	}
}
