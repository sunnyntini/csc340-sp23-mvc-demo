package com.csc340.mvcdemo.Controller;

import com.csc340.mvcdemo.model.University;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author sunny
 */
@Controller
public class UniversityController {

    @GetMapping("/universities")
    public String getUniversities(Model model) {

        try {
            String url = "http://universities.hipolabs.com/search?name=sports";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String universities = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(universities);

            List<University> universityList = new ArrayList<>();

            for (JsonNode rt : root) {
                String name = rt.get("name").asText();
                String country = rt.get("country").asText();
                String domain = rt.get("domains").get(0).asText();
                University uni = new University(name, country, domain);
                universityList.add(uni);

            }

            model.addAttribute("universities", universityList);
            return "universities";
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UniversityController.class.getName()).log(
                    Level.SEVERE,
                    null, ex);
             model.addAttribute("message", "error in /universities");
            return "error";
        }
    }
}
