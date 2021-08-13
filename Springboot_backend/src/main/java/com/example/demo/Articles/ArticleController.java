package com.example.demo.Articles;
import java.util.List;
import java.util.Optional;

import com.example.demo.AppUser.AppUser;
import com.example.demo.AppUser.AppUserRepository;
import com.example.demo.AppUser.AppUserService;
import com.example.demo.Conference.Conference;
import com.example.demo.Conference.ConferenceRepository;
import com.example.demo.Conference.ConferenceService;
import com.example.demo.Registration.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "api/articles")

public class ArticleController {

    @Autowired
    private FileService fileService;

    @Autowired
    private JwtUtility jwtTokenUtil;
    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;
    private final ConferenceRepository conferenceRepository;
    private final ConferenceService conferenceService;
    private final ArticleService articleService;

    @Autowired
    public ArticleController(AppUserService appUserService, AppUserRepository appUserRepository, ConferenceRepository conferenceRepository, ConferenceService conferenceService, ArticleService articleService) {
        this.appUserService = appUserService;
        this.appUserRepository = appUserRepository;
        this.conferenceRepository = conferenceRepository;
        this.conferenceService = conferenceService;
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getArticles() {
        return articleService.getArticles();
    }

    @GetMapping("/Article/{articleId}")
    public Optional<Article> getArticle(@PathVariable("articleId") Long articleId) {

        File file = fileService.getFile(articleId);

        var fL =ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getData()));

        return articleService.getArticle(articleId);



    }


    @DeleteMapping(path = "{articleId}")
    public void deleteArticle(
            @PathVariable("articleId") Long articleId){
        articleService.deleteArticle(articleId);

    }

    @PutMapping(path = "{articleId}")
    public void updateArticle(
            @PathVariable("articleId") Long articleId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String titre,
            @RequestParam(required = false) String resume,
            @RequestParam(required = false) String mots_cle,
            @RequestParam(required = false) String theme){
        articleService.updateArticle(articleId, name, titre, resume, mots_cle, theme, fileService.getFile(articleId));
    }

    @PostMapping(path = "/add/{confId}" ,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_OCTET_STREAM_VALUE}, produces = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public String postConf(@RequestPart("request") Article article , @RequestHeader String authorization,@PathVariable("confId") Long confId,@RequestPart("file") MultipartFile file){

        String token = null;
        String userName = null;

        if(null != authorization && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            userName = jwtTokenUtil.getUsernameFromToken(token);
        }
        AppUser userObj = (AppUser) appUserService.loadUserByUsername(userName);
        articleService.addNewArticle(article,userName,confId);
        Long Conf= article.getId();
        System.out.println("hdi id article"+ Conf );
        Optional<Conference> conferen= conferenceService.getConferences(confId);
        Conference cc =conferen.orElseThrow(()-> new IllegalStateException("Conference does not exists prblmmmmmmmm")) ;
        System.out.println("hdi onference"+conferen );
        String Confstrng=Long.toString(Conf);
        System.out.println("hna yafficher 0"+ Confstrng );
        cc.addIdArticles(Confstrng,cc.getidArticlesString());
        conferenceRepository.save(cc);
//        conferen.setIdArticles(Conf);
//        conferenceRepository.save(conferen);
        userObj.addIdArticles(Confstrng);
        appUserRepository.save(userObj);
        System.out.println(Conf);
        System.out.println(userObj);
        File pfile = fileService.storeFile(file,Conf);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .toUriString();

        articleService.updateArticle(article.getId(), article.getName(),article.getTitre(),article.getResumer(),article.getMots_cle(), article.getTheme(), pfile);

//        article.setFile(pfile);
//        articleService.addNewArticle(article,userName,confId);

        return "fileName : " + file.getOriginalFilename() + "\n" + fileDownloadUri + pfile.getId()
                + "\n id : " + pfile.getId()
                +"data : " + pfile.getData()
                + "article" + article.toString();


    }


}
