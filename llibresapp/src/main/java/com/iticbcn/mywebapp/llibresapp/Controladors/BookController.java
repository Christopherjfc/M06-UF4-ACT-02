package com.iticbcn.mywebapp.llibresapp.Controladors;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.iticbcn.mywebapp.llibresapp.Model.Llibre;
import com.iticbcn.mywebapp.llibresapp.Model.Usuaris;
import com.iticbcn.mywebapp.llibresapp.Serveis.LlibreService;

@Controller
@SessionAttributes("users")
public class BookController {

    @Autowired
    private LlibreService llibreService;

    @GetMapping("/")
    public String iniciar(Model model) {
        return "login";
    }

    // botón "accedir" en el login agarramos su el endpoint "/index"
    @PostMapping("/index")
    public String login(@ModelAttribute("users") Usuaris users, Model model) {

        model.addAttribute("users", users);

        if (users.getUsuari().equals("Christopher") 
        && users.getPassword().equals("12345")) {
            return "index";
        } else {
            return "login";
        }        
    }

        @PostMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        return "redirect:/";
    }



    @GetMapping("/index")
    public String index(@ModelAttribute("users") Usuaris users, Model model) {
        return "index";
    }


    @GetMapping("/consulta") 
    public String consulta(@ModelAttribute("users") Usuaris users,Model model) {

        // Obté tots els llibres de la BBDD
        Set<Llibre> llibres = llibreService.obtenirTotsElsLlibres();

        model.addAttribute("llibres", llibres);
        
        return "consulta";
    }


    @GetMapping("/inserir") 
    public String inputInserir(@ModelAttribute("users") Usuaris users,Model model) {

        /*
         * Esta instrucción crea un nuevo objeto de Llibre, que lo añade a la vista
         * antes de cargar el formulario que se encuentra en el HTML "inserir.html".
         * 
         * Al usar la dependencia Thymelead, con th:object = "${llibre}" en el HTML 
         * indico que el formulario se trabajará con un Llibre.
         */
        
        
        model.addAttribute("llibre", new Llibre());
        return "inserir";
    }
    

    @GetMapping("/cercaid")
    public String inputCerca(@ModelAttribute("users") Usuaris users, Model model) {

        Llibre llibre = new Llibre();
        model.addAttribute("llibreErr", true);
        model.addAttribute("message", "");
        model.addAttribute("llibre", llibre);

        return "cercaid";

    }

    // Esto habilita el botón de "desar" en inserir un nou llibre
    @PostMapping("/inserir")
    public String inserir(@ModelAttribute("users") Usuaris users, @ModelAttribute Llibre llibre, Model model) {
        /*
         * Utilizo la anotación @ModelAttribute para el libro, ya que a la hora de que se llenen los campos
         * en la página web y se le de al botón "desar", @ModelAttribute crea un objeto Llibre y asigna
         * los valores de los campos del formulario al objeto Llibre del parámetro.
         * 
         * Al tener el llibre cargado, lo guardo con el método "desaLlibre(Llibre)".
         */

        // Si no se lllega a insertar el libro (libreErr = true)
        boolean llibreErr = !llibreService.desaLlibre(llibre);

        // El mensaje solo se mostrará si hay un error en el insert
        String message = "L'ISBN no és vàlid. Ha de tenir 13 dígits i començar per 978 o 979.";
        
        model.addAttribute("message", message);
        model.addAttribute("llibreErr", llibreErr);
        
        // Redirijo a la misma página con el mensaje de error en pantalla Si el ISBN no es válido
        // Si se guardó con éxito, lo redirijo a "consulta".
        return  llibreErr ? "inserir" : "redirect:/consulta";
    
    }


    @PostMapping("/cercaid")
    public String cercaId(@ModelAttribute("users") Usuaris users,
                          @RequestParam(name = "idllibre", required = false) String idLlibre, 
                          Model model) {
        
        long idLlib = 0;
        String message = "";
        boolean llibreErr = false;

        try {
            idLlib = Long.parseLong(idLlibre);
            
            // Si el liibro es encontrado, lo guarda en el contendeor Optional
            Optional<Llibre> llibreOpcional = llibreService.findByIdLlibre(idLlib);
            
            if(llibreOpcional.isPresent()) {
                // Uso el método get() para obtener el Libro que se guardó en el contenedor Optional
                model.addAttribute("llibre", llibreOpcional.get());
            } else {
                message = "No hi ha cap llibre amb aquesta id";
                llibreErr = true;
            }

        } catch (Exception e) {
            message = "La id de llibre ha de ser un nombre enter";
            llibreErr = true;
        } 
        
        model.addAttribute("message", message);
        model.addAttribute("llibreErr",llibreErr);

        return "cercaid";

    }



    /*cada vez que arranque una nueva sesión, 
    lo que hace este método es inicializar el usuario*/

    @ModelAttribute("users")
    public Usuaris getDefaultUser() {
        return new Usuaris(); 
    }

}
