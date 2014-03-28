package osgi.translator.client;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;

import org.ops4j.peaberry.Export;
import org.ops4j.peaberry.Peaberry;
import org.ops4j.peaberry.util.TypeLiterals;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.google.inject.AbstractModule;

import osgi.translator.service.TranslatorService;

import static com.google.inject.Guice.createInjector;
import static org.ops4j.peaberry.Peaberry.osgiModule;

public class Activator
    extends AbstractModule
    implements BundleActivator
{
    @Inject
    Export<HttpServlet> translatorServlet;

    @Override
    protected void configure()
    {
        bind( TypeLiterals.iterable( TranslatorService.class ) ).toProvider( Peaberry.service( TranslatorService.class ).multiple() );

        HashMap<String, Object> attr = new HashMap<>();
        attr.put( "alias", "/ost" );

        bind( TypeLiterals.export( HttpServlet.class ) ).
            toProvider( Peaberry.service( TranslatorServlet.class ).
                attributes( attr ).export() );
    }

    @Override
    public void start( final BundleContext context )
        throws Exception
    {
        System.out.println( "Starting translator" );

        createInjector( osgiModule( context ), this ).injectMembers( this );
    }

    @Override
    public void stop( final BundleContext context )
        throws Exception
    {
        translatorServlet.unget();
    }
}
