package osgi.translator.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;

import osgi.translator.service.TranslatorService;

public class TranslatorServlet
    extends HttpServlet
{
    @Inject
    Iterable<TranslatorService> translatorServices;

    @Override
    protected void doGet( final HttpServletRequest req, final HttpServletResponse resp )
        throws ServletException, IOException
    {
        resp.getWriter().print( "hallo" );

        System.out.println( "TranslatorServices: " + translatorServices );
    }

    /*

    public void start()
    {
        System.out.println( "Starting client, services" );
    }

    public void add( final TranslatorService translatorService )
    {
        System.out.println( "Added service: " + translatorService );
    }

    public void remove( final TranslatorService translatorService )
    {
        System.out.println( "Removed service: " + translatorService );
    }
    */

}
