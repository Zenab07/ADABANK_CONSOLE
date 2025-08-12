package ci.ada.services.interfaces;

public interface EmailService {
    void sendEmail(String destinataire, String sujet, String message);
}