package osgi.translator.service.impl;

import javax.inject.Inject;

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
    private Export<TranslatorService> engTrans;

    @Override
    protected void configure()
    {
        bind( TypeLiterals.export( TranslatorService.class ) ).toProvider( Peaberry.service( EnglishTranslator.class ).export() );
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
        System.out.println( "Stopping translator" );

        engTrans.unget();
    }

}
