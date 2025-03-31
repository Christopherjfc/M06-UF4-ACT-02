package com.iticbcn.mywebapp.llibresapp.Serveis;

import java.util.Optional;
import java.util.Set;

import com.iticbcn.mywebapp.llibresapp.Model.Llibre;

public interface LlibreService {
    Set<Llibre> obtenirTotsElsLlibres();
    Llibre obtenirPerTitol(String titol);
    Set<Llibre> obtenirPerTitolEditorial(String titol, String editorial);
    Optional<Llibre> findByIdLlibre(Long idLlibre);
    boolean validaISBN(String isbn);
    boolean desaLlibre(Llibre llibre);
}
