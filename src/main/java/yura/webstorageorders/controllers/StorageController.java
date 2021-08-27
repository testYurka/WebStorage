package yura.webstorageorders.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yura.webstorageorders.model.Storage;
import yura.webstorageorders.service.StorageService;

@Controller
@RequestMapping("/")
public class StorageController {
    @Autowired
    private StorageService storageService;

    @GetMapping("/storage")
    public String main(Model model)  {
        Iterable<Storage> storages = storageService.findAll();
        model.addAttribute("storages", storages);
        return "storage";
    }

    @PostMapping("/storage")
    public String addOrder(@RequestParam String item, @RequestParam Double price, @RequestParam Long quantity, Model model) {
        Storage storage = new Storage(item, price, quantity);
        storageService.save(storage);
        Iterable<Storage> storages = storageService.findAll();
        model.addAttribute("storages", storages);
        return "storage";
    }

    @DeleteMapping("/{storageId}")
    @ResponseBody
    public void delete(@PathVariable("storageId") Long id) {
        storageService.delete(id);
    }

    @PutMapping("/{storageId}")
    @ResponseBody
    public Storage update(@PathVariable("storageId") Long storageId, @RequestBody Storage storage) {
        return storageService.update(storage, storageId);
    }

}
