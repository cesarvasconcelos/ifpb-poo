package br.edu.ifpb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Universidade {
    List<Funcionário> lista = new ArrayList<>();

    public boolean adicionarFuncionário( Funcionário func )
    {
        if ( Objects.isNull( func ) ) return false;

        for ( Funcionário temp : lista )
            if ( temp.getMatrícula() == func.getMatrícula() )
                return false;

        return lista.add( func );
    }

    public Funcionário buscarFuncionário( int matrícula )
    {
        for ( Funcionário f : lista )
            if( f.getMatrícula() == matrícula ) return f;
        return null;
    }
}
