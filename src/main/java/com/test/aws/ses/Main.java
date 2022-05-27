package com.test.aws.ses;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

public class Main {

    private static final String FROM = "cchaves@agap2.pt";
    private static final String TO = "cchaves@agap2.pt";
    private static final String SUBJECT = "Test AWS SES";
    private static final String HTMLBODY = "<h1>Test AWS SES</h1>";

    public static void main(String args[]) throws Exception {
       Main main = new Main();
       main.run();
    }

    public void run() {
        try {
            AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
                  .withRegion(Regions.EU_WEST_1).build();

            SendEmailRequest request = new SendEmailRequest()
                .withDestination(new Destination().withToAddresses(TO))
                .withMessage(new Message().withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(HTMLBODY)))
                .withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
                .withSource(FROM);

            client.sendEmail(request);

            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: " + ex.getMessage());
        }
    }
}
