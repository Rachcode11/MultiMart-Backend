package com.semicolon.africa.jumiaApp.registration;

import com.semicolon.africa.jumiaApp.data.model.User;
import com.semicolon.africa.jumiaApp.data.model.VerificationOTP;
import com.semicolon.africa.jumiaApp.data.repository.UserRepository;
import com.semicolon.africa.jumiaApp.dtos.request.RegistrationRequest;
import com.semicolon.africa.jumiaApp.dtos.request.SendOTPRequest;
import com.semicolon.africa.jumiaApp.dtos.request.VerifyOtpRequest;
import com.semicolon.africa.jumiaApp.email.EmailSender;
import com.semicolon.africa.jumiaApp.enums.Role;
import com.semicolon.africa.jumiaApp.exceptions.GenericHandlerException;
import com.semicolon.africa.jumiaApp.service.user.UserService;
import com.semicolon.africa.jumiaApp.utils.OTPGenerator;
import com.semicolon.africa.jumiaApp.verification.VerificationOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationOtpService verificationOtpService;

    @Autowired
    private EmailSender emailSender;





    @Override
        public String register(RegistrationRequest registrationRequest) throws GenericHandlerException {
            boolean isExist = userService.findUserByEmailAddress(registrationRequest.getEmailAddress())
                    .isPresent();
            if (isExist)throw new GenericHandlerException("User with email already exist");
            String oTP =  userService.createAccount(new User(
                    registrationRequest.getFirstName(),
                    registrationRequest.getLastName(),
                    registrationRequest.getUserName(),
                    registrationRequest.getEmailAddress(),
                    passwordEncoder.encode(registrationRequest.getPassword()),
                    Role.USER
            ));

            emailSender.send(registrationRequest.getEmailAddress(), buildEmail(registrationRequest.getFirstName(), oTP));

            return oTP;
        }

    @Override
    public String verifyOtp(VerifyOtpRequest verifyOtpRequest) {
        VerificationOTP otp = verificationOtpService.findByOTP(verifyOtpRequest.getOneTimePassword())
                .orElseThrow(()-> new GenericHandlerException("OneTimePassword is invalid"));

        if (otp.getExpiredAt().isBefore(LocalDateTime.now())){
            throw new GenericHandlerException("token has expired ");

        }
        if(otp.getVerifiedAt() != null){
            throw new GenericHandlerException("token has been used");
        }
        userService.enableUser(verifyOtpRequest.getEmailAddress());
        verificationOtpService.setVerifiedAt(otp.getOneTimePassword());


        return "verified";
    }

    @Override
    public String reSendVerificationOtp(SendOTPRequest resendOTPRequest) {
        User foundUser = userRepository.findByEmailAddressIgnoreCase(resendOTPRequest.getEmailAddress())
                .orElseThrow(()-> new GenericHandlerException("email not found"));
        if (!foundUser.getIsDisabled())throw new GenericHandlerException("you are verified user");
        String oTp = OTPGenerator.generateOtp().toString();
        VerificationOTP verificationOTP = new VerificationOTP(
                oTp,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                foundUser
        );
        verificationOtpService.saveVerificationOTP(verificationOTP);
        emailSender.send(resendOTPRequest.getEmailAddress(), buildEmail(foundUser.getFirstName(),oTp));
        return oTp;
    }


    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">" + link + "</p></blockquote>\n Link will expire in 10 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }


}

