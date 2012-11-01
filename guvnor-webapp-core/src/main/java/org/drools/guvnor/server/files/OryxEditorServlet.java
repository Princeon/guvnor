package org.drools.guvnor.server.files;

import org.drools.guvnor.client.rpc.Asset;
import org.drools.guvnor.client.rpc.RuleFlowContentModel;
import org.drools.guvnor.server.RepositoryAssetService;
import org.drools.guvnor.server.util.LoggingHelper;
import org.drools.guvnor.client.rpc.Path;
import org.drools.guvnor.client.rpc.PathImpl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OryxEditorServlet extends HttpServlet {

    private static final LoggingHelper log = LoggingHelper.getLogger(OryxEditorServlet.class);

    @Inject
    private RepositoryAssetService repositoryAssetService;

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException,
            IOException {
        log.debug("Incoming request from Oryx Designer:" + request.getRequestURL());

        //action never used. Why? - JT
        String action = request.getParameter("action");
        String uuid = request.getParameter("uuid");
        String usr = request.getParameter("usr");
        String pwd = request.getParameter("pwd");

        if (uuid == null) {
            throw new ServletException(new IllegalArgumentException("Parameter uuid not specified."));
        }
        //action never used. Why? - JT
        /*if (action == null) {
            action = "json";
        } */

        // log in
//        credentials.setUsername(usr);
//        credentials.setCredential(new org.picketlink.idm.impl.api.PasswordCredential(pwd));

//      TODO How to do this with Uberfire? -Rikkola-
//        identity.login();
//        if ( !identity.isLoggedIn() ) {
//            throw new ServletException(new IllegalArgumentException("Unable to authenticate user."));
//        }
        log.debug("Successful login");

        try {
        	//TODO: refactor OryxEditor to use Path (to sned the request using Path as parameter) instead of UUID          	
    		Path path = new PathImpl();
    		path.setUUID(uuid);
            Asset asset = repositoryAssetService.loadRuleAsset(path);
            if (asset.getContent() != null) {
                response.setContentType("application/xml");
                response.setCharacterEncoding("UTF-8");
                String content = asset.getContent().toString();
                if (asset.getContent() instanceof RuleFlowContentModel) {
                    content = ((RuleFlowContentModel) asset.getContent()).getXml();
                }

                if (content != null) {
                    response.getOutputStream().write(content.getBytes("UTF-8"));
                    response.getOutputStream().close();
                } else {
                    setDefaultResponse(response);
                }

            } else {
                setDefaultResponse(response);
            }
        } catch (Throwable t) {
            log.error(t.getMessage(),
                    t);
            setDefaultResponse(response);
        }

    }

    private void setDefaultResponse(HttpServletResponse response) throws ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String result = "";
        response.setContentLength(result.length());
        try {
            response.getOutputStream().write(result.getBytes());
            response.getOutputStream().close();
        } catch (IOException e) {
            throw new ServletException(e.getMessage());
        }
    }
}
