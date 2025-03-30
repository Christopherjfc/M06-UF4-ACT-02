package com.iticbcn.mywebapp.llibresapp.Serveis;

import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iticbcn.mywebapp.llibresapp.Model.Llibre;
import com.iticbcn.mywebapp.llibresapp.Repositoris.Repositori;

@Service
public class LlibreServiceImpl implements LlibreService {

    @Autowired
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

    public boolean validaISBN(String isbn) {
        /*
         * Retorna true si son 13 números
         * que empiece por 978 o 979 y que
         * sean solo números
         */
        String isbn13Regex = "^(978|979)\\d{10}$";
        return Pattern.matches(isbn13Regex, isbn);
    }

    public boolean desaLlibre(Llibre llibre) {
        if (!validaISBN(llibre.getIsbn())) {
            return false;
        }

        // Se guarda si el ISBN es correcto
        repositori.save(llibre);
        return true;
    }
    
}
