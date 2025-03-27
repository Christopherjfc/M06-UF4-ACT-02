package com.iticbcn.mywebapp.llibresapp.Serveis;

import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import com.iticbcn.mywebapp.llibresapp.Model.Llibre;
import com.iticbcn.mywebapp.llibresapp.Repositoris.Repositori;

public class LlibreServiceImpl implements LlibreService {

    private Repositori repositori;
    @Override
    public Set<Llibre> obtenirTotsElsLlibres() {
        return repositori.findAll();
    }

    @Override
    public Llibre obtenirPerTitol(String titol) {
        return repositori.findByTitol(titol);
    }

    @Override
    public Set<Llibre> obtenirPerTitolEditorial(String titol, String editorial) {
        return repositori.findByTitolAndEditorial(titol, editorial);
    }

    @Override
    public Optional<Llibre> findByIdLlibre(Long idLlibre) {
        return repositori.findById(idLlibre);
    }

    @Override
    public boolean validaISBN(String isbn) {
        /*
         * Retorna true si son 13 números
         * que empiece por 978 o 979 y que
         * sean solo números
         */
        String isbn13Regex = "^(978|979)\\d{10}$";
        return Pattern.matches(isbn13Regex, isbn);
    }
    
}
