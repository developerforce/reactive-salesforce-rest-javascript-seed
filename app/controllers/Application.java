package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.F;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        if ((Secured.getConsumerKey() == null) || (Secured.getConsumerSecret() == null)) {
            return internalServerError(setup.render(Secured.isSecure(request()), Secured.getOauthCallbackUrl(request())));
        } else {
            return ok(index.render());
        }
    }

    @Security.Authenticated(Secured.class)
    public static F.Promise<Result> getContacts() {
        F.Promise<JsonNode> contacts = SalesforceAPI.query(Secured.getToken(), Secured.getInstanceUrl(), "select FirstName, LastName from Contact");
        return contacts.map(new F.Function<JsonNode, Result>() {
            @Override
            public Result apply(JsonNode jsonNode) throws Throwable {
                return ok(jsonNode);
            }
        });
    }

}
