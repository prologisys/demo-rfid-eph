package com.prolosys.rfid.common.mail.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component("templateEmails")
public class TemplateEmails {
	
	private String replaceKeys(StringBuilder template, HashMap<String, String> map) {
		int start, length = 0;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			start = template.indexOf("%" + entry.getKey() + "%");
			length = ("%" + entry.getKey() + "%").length();
			template.replace(start, (start + length), entry.getValue());
		}

		return template.toString();
	}

	public String toVerifyEmail(HashMap<String, String> map) {

		StringBuilder template = new StringBuilder();
		template.append("<!doctype html>");
		template.append("<html>");
			template.append("<head>");
				template.append("<meta charset='utf-8'>");
			template.append("</head>");
			template.append("<body>");
				template.append("<p>¡Hola!, %firstname%.</p>");
				template.append("<p>Gracias por registrarte en AgroBusinessMeet.</p>");
				template.append("<p>Para terminar el proceso de registro debes confirmar tu correo haciendo click en el siguiente enlace: <a href='%frontendUrlCheckemail%'>%textFrontendUrlCheckemail%</a></p>");
				template.append("<p>Equipo de AgroBusinessMeet</p>");
			template.append("</body>");
		template.append("</html>");
		
		return replaceKeys(template, map);

	}
	
	public String recoverPassword(HashMap<String, String> map) {

		StringBuilder template = new StringBuilder();
		template.append("<!doctype html>");
		template.append("<html>");
			template.append("<head>");
				template.append("<meta charset='utf-8'>");
			template.append("</head>");
			template.append("<body>");
				template.append("<p>¡Hola!, %firstname%.</p>");
				template.append("<p>Recientemente has solicitado el cambio de tu contraseña.</p>");
				template.append("<p>Para continuar con el proceso debes ir al siguiente enlace: <a href='%frontendUrl%'>Cambiar contraseña</a></p>");
				template.append("<p>Equipo de SCL</p>");
			template.append("</body>");
		template.append("</html>");
		
		return replaceKeys(template, map);

	}
	
	
	public String toChangePassword(HashMap<String, String> map) {
		
		StringBuilder template = new StringBuilder();
		template.append("<!doctype html>");
		template.append("<html>");
			template.append("<head>");
				template.append("<meta charset='utf-8'>");
			template.append("</head>");
			template.append("<body>");
				template.append("<p>¡Hola!, %firstname%.</p>");
				template.append("<p>Parece que ha olvidado su contraseña y ha solicitado una nueva.</p>");
				template.append("<p>Su nueva contraseña es: %password%</p>");
				template.append("<p>Por favor, ingrese a su cuenta <a href='%frontendUrl%'>Aquí</a>.</p>");
				template.append("<p>Equipo de AgroBusinessMeet</p>");
			template.append("</body>");
		template.append("</html>");
		return replaceKeys(template, map);
	}


	public String toNotifyAppointmentRequest(HashMap<String, String> map) {
		
		StringBuilder template = new StringBuilder();
		template.append("<!doctype html>");
		template.append("<html>");
			template.append("<head>");
				template.append("<meta charset='utf-8'>");
			template.append("</head>");
			template.append("<body>");
				template.append("<p>¡Hola!, %firstnameReceiver%.</p>");
				template.append("<p>%firstnameSolicitant%, representante de la empresa %companyName%, le ha solicitado una cita (folio: %folio%), con el objetivo '%affair%'.</p>");
				template.append("<p>Para responder a la cita, por favor, accede al portal de AgroBusinessMeet, dando clic en <a href='%frontendUrl%'>este enlace</a>.</p>");
				template.append("<p>Equipo de AgroBusinessMeet</p>");
			template.append("</body>");
		template.append("</html>");
		return replaceKeys(template, map);
	}

	
	public String toNotifyAcceptedAppointment(HashMap<String, String> map) {
		StringBuilder template = new StringBuilder();
		template.append("<!doctype html>");
		template.append("<html>");
			template.append("<head>");
				template.append("<meta charset='utf-8'>");
			template.append("</head>");
			template.append("<body>");
				template.append("<p>¡Hola!, %firstnameSolicitant%.</p>");
				template.append("<p>%firstnameReceiver%, representante de la empresa %companyName%, ha <strong>Aceptado</strong> su solicitud de cita con folio (%folio%).</p>");
				template.append("<p>Sin embargo, antes de visualizar el día, hora y lugar es necesario realizar el pago o pagar con el saldo de su cuenta.</p>");
				template.append("<p>Ingrese a su cuenta haciendo click en <a href='%frontendUrl%'>este enlace</a>.</p>");
				template.append("<p>Equipo de AgroBusinessMeet</p>");
			template.append("</body>");
		template.append("</html>");
		return replaceKeys(template, map);
	}


	
	
	
	
	
	public String toNotifyRejectedAppointment(HashMap<String, String> map) {
		StringBuilder template = new StringBuilder();
		template.append("<!doctype html>");
		template.append("<html>");
			template.append("<head>");
				template.append("<meta charset='utf-8'>");
			template.append("</head>");
			template.append("<body>");
				template.append("<p>¡Hola!, %firstnameSolicitant%.</p>");
				template.append("<p>%firstnameReceiver%, representante de la empresa %companyName%, ha <strong>Rechazado</strong> su solicitud de cita con folio (%folio%).</p>");
				template.append("<p>Puedes solicitar una nueva cita en el portal de AgroBusinessMeet, haciendo click en <a href='%frontendUrl%'>este enlace</a>.</p>");
				template.append("<p>Equipo de AgroBusinessMeet</p>");
			template.append("</body>");
		template.append("</html>");
		return replaceKeys(template, map);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String toNotifyPaymentForAppointment(HashMap<String, String> map) {
		
		StringBuilder template = new StringBuilder();
		template.append("<!doctype html>");
		template.append("<html>");
			template.append("<head>");
				template.append("<meta charset='utf-8'>");
			template.append("</head>");
			template.append("<body>");
				template.append("<p>¡Hola!, %firstname%.</p>");
				template.append("<p>%payer%, representante de la empresa %payerCompany%, ha realizado el pago de la cita (%folio%).</p>");
				template.append("<p>Accede al portal de AgroBusinessMeet para validarlo, haciendo click en <a href='%frontendUrl%'>este enlace</a>.</p>");
				template.append("<p>Equipo de AgroBusinessMeet</p>");
			template.append("</body>");
		template.append("</html>");
		return replaceKeys(template, map);
	}


	
	
	
	
	public String toNotifyValidatedAppointmentSolicitant(HashMap<String, String> map) {
		
		StringBuilder template = new StringBuilder();
		template.append("<!doctype html>");
		template.append("<html>");
			template.append("<head>");
				template.append("<meta charset='utf-8'>");
			template.append("</head>");
			template.append("<body>");
				template.append("<p>¡Hola!, %firstnameSolicitant%.</p>");
				template.append("<p>Tu cita (%folio%) con el representante %firstnameReceiver% de la empresa %companyReceiver% esta lista.</p>");
				template.append("<p>Accede al portal de AgroBusinessMeet para más detalles, dando click en <a href='%frontendUrl%'>este enlace</a>.</p>");
				template.append("<p>Equipo de AgroBusinessMeet</p>");
			template.append("</body>");
		template.append("</html>");
		return replaceKeys(template, map);
	}


	public String toNotifyValidatedAppointmentReceiver(HashMap<String, String> map) {
		
		StringBuilder template = new StringBuilder();
		template.append("<!doctype html>");
		template.append("<html>");
			template.append("<head>");
				template.append("<meta charset='utf-8'>");
			template.append("</head>");
			template.append("<body>");
				template.append("<p>¡Hola!, %firstnameReceiver%.</p>");
				template.append("<p>Tu cita (%folio%) con el representante %firstnameSolicitant% de la empresa %companySolicitant% esta lista.</p>");
				template.append("<p>Accede al portal de AgroBusinessMeet para más detalles, dando click en <a href='%frontendUrl%'>este enlace</a>.</p>");
				template.append("<p>Equipo de AgroBusinessMeet</p>");
			template.append("</body>");
		template.append("</html>");
		return replaceKeys(template, map);
	}

}
