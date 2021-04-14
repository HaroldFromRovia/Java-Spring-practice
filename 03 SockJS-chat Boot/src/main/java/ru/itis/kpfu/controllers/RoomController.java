package ru.itis.kpfu.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.kpfu.services.interfaces.CookieService;
import ru.itis.kpfu.services.interfaces.MessageService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class RoomController {

    private final MessageService messageService;
    private final CookieService cookieService;

    @GetMapping("/room/{id}")
    public String getIndexPage(Model model, @PathVariable Long id, HttpServletRequest request) {
        model.addAttribute("messages", messageService.getAllByRoomId(id));
        var cookie = cookieService.findCookie(request.getCookies(), "from");
        if (cookie.isPresent())
            model.addAttribute("from", cookie.get().getValue());
        else return "redirect:/signIn";
        return "index";
    }
}
