import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class CatálogoTest {
    static final Camisa camisaInfantil =
            new Camisa( "Infantil manga curta lisa e sem gola", TAMANHO_CAMISA_EN.P );
    static final Camisa camisaSocial =
            new Camisa( "Masculina social manga longa 100% algodão com punho e gola branca", TAMANHO_CAMISA_EN.G );
    static final Camisa camisaGestante =
            new Camisa( "Gestante modelo bata com manga curta e foto do bebê", TAMANHO_CAMISA_EN.XL );

    private Catálogo catálogo = new Catálogo();

    @Test
    public void testDeveAdicionarCamisa()
    {
        catálogo.adicionarCamisa( camisaInfantil );
        catálogo.adicionarCamisa( camisaSocial );

        assertThat( catálogo, containsInAnyOrder( camisaSocial, camisaInfantil ) );
        assertThat( catálogo.tamanho(), is( 2 ) );
    }

    @Test
    public void testDeveRemoverCamisa()
    {
        catálogo.adicionarCamisa( camisaInfantil );
        catálogo.adicionarCamisa( camisaSocial );
        catálogo.adicionarCamisa( camisaGestante );

        catálogo.removerCamisa( camisaGestante );
        assertThat( catálogo, not( contains( camisaGestante ) ) );
    }

    @Test
    public void testDeveSubstituirCamisa()
    {
        catálogo.adicionarCamisa( camisaInfantil );
        catálogo.adicionarCamisa( camisaSocial );

        // testar substituição de camisa existente
        catálogo.substituir( camisaInfantil, camisaGestante );
        assertThat( catálogo, contains( camisaGestante, camisaSocial ) );
    }
    @Test
    public void testNãoDeveSubstituirCamisa()
    {
        catálogo.adicionarCamisa( camisaInfantil );

        // testar substituição de camisa *não* existente
        catálogo.substituir( camisaGestante, camisaSocial );
        catálogo.substituir( camisaGestante, camisaInfantil );

        assertThat( catálogo, not( contains( camisaGestante ) ) );
        assertThat( catálogo, not( contains( camisaSocial ) ) );

        assertThat( catálogo, contains( camisaInfantil ) );
    }

    @Test
    public void testDeveRetornarCamisasExtraGrandes()
    {
        catálogo.adicionarCamisa( camisaInfantil );
        catálogo.adicionarCamisa( camisaSocial );
        catálogo.adicionarCamisa( camisaGestante );

        assertThat( catálogo.getCamisasExtraGrandes(), contains( camisaGestante ) );
    }

    @Test
    public void testNãoDeveRetornarCamisasExtraGrandes()
    {
        catálogo.adicionarCamisa( camisaInfantil );
        catálogo.adicionarCamisa( camisaSocial );

        assertThat( catálogo.getCamisasExtraGrandes(), emptyIterable() );
    }
}
// pom.xml:
    //<dependencies>
    //    <dependency>
    //        <groupId>junit</groupId>
    //        <artifactId>junit</artifactId>
    //        <version>4.12</version>
    //        <scope>test</scope>
    //    </dependency>
    //    <dependency>
    //        <groupId>org.hamcrest</groupId>
    //        <artifactId>hamcrest-library</artifactId>
    //        <version>1.3</version>
    //        <scope>test</scope>
    //    </dependency>
    //</dependencies>