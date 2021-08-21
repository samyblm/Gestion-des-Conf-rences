package com.example.demo.Conference;

import com.example.demo.AppUser.AppUser;
import com.example.demo.AppUser.AppUserRepository;
import com.example.demo.Articles.Article;
import com.example.demo.Articles.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrebuteService {
    private final AppUserRepository appUserRepository;
    private final ConferenceRepository conferenceRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public DistrebuteService(ConferenceService conferenceService, AppUserRepository appUserRepository,
            ConferenceRepository conferenceRepository, ArticleRepository articleRepository) {
        this.appUserRepository = appUserRepository;
        this.conferenceRepository = conferenceRepository;
        this.articleRepository = articleRepository;
    }

    public List<String> getspec(Long conferenceId) {
        List<String> spec = new ArrayList<>();
        List<String> Idrev;
        Conference conf = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new IllegalStateException("conf texistish;"));

        Idrev = conf.getidViewersFinal();
        System.out.println(Idrev);

        for (String Value : Idrev) {

            AppUser useobj = appUserRepository.findByEmail(Value)
                    .orElseThrow(() -> new IllegalStateException("user yexestish"));
            System.out.println(useobj.getEmail());
            System.out.println(useobj.getspecialite());
            System.out.println(!spec.contains(useobj.getspecialite()));
            if (!spec.contains(useobj.getspecialite())) {
                spec.add(useobj.getspecialite());
            }
        }
        return spec;
    }

    public void Distrubute(Long conferenceId) {
        System.out.println("ok test");

        Conference conf = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new IllegalStateException("conf texistish;"));

        List<String> Specialite = getspec(conferenceId);
        List<String> reviewers = conf.getidViewersFinal();
        List<String> articlesid = conf.getidArticles();

        System.out.println("List01 specialite" + Specialite);
        System.out.println("List02 reviewers" + reviewers);
        System.out.println("List03 articlesid" + articlesid);

        for (String Value : Specialite) {

            List<String> revspec = new ArrayList<>();
            List<String> articleid = new ArrayList<>();
            List<String> List = new ArrayList<>();

            for (String var : reviewers) {
                System.out.println("var rev" + var);
                AppUser useobj = appUserRepository.findByEmail(var)
                        .orElseThrow(() -> new IllegalStateException("user yexestish"));
                System.out.println("username rev" + useobj.getUsername());
                System.out.println("start test cond");
                System.out.println(useobj.getspecialite() + "spec");
                System.out.println(useobj.getspecialite().toString());
                System.out.println(useobj.getspecialite() == Value);
                System.out.println(Value);
                System.out.println(useobj.getspecialite().equals(Value));
                System.out.println("end test cond");
                if (useobj.getspecialite().equals(Value)) {
                    System.out.println(
                            "condition rev " + useobj.getspecialite() + Value + useobj.getspecialite() == Value);
                    revspec.add(var);
                }

            }
            System.out.println("reviewers with spec" + Value + revspec);

            for (String var2 : articlesid) {
                System.out.println("var article" + var2);
                Article article = articleRepository.findById(Long.parseLong(var2))
                        .orElseThrow(() -> new IllegalStateException("artile yexestish"));
                System.out.println("Id article" + article.getId());
                if (article.getTheme().equals(Value)) {
                    articleid.add(var2);
                }

            }
            System.out.println("articles with spec" + Value + articleid);

            int A = articleid.size();
            int R = revspec.size();
            System.out.println("size A" + A);
            System.out.println("size R" + R);

            if (R >= A) {

                for (String var : revspec) {
                        Boolean verify = false;

                        if (!List.isEmpty()) {


                            for (String value : List) {
                                if (verify == false) {
                                    String nameRev = var;
                                    AppUser apuser = appUserRepository.findByEmail(nameRev)
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    String toadd = apuser.getIdArticlesCorrigString();
                                    AppUser chercheuruse = appUserRepository.findById(Long.parseLong(value))
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    String idarticle = value;

                                    if (chercheuruse.getLocation() != apuser.getLocation()) {

                                        if (toadd == "") {
                                            toadd = idarticle;
                                        } else {
                                            toadd = toadd + idarticle;
                                        }
                                        articleid.remove(idarticle);
                                        System.out.println("List toadd" + toadd);
                                        apuser.setIdArticlesCorrigString(toadd);
                                        appUserRepository.save(apuser);
                                        verify = true;

                                    }
                                }
                            }

                             if(!articleid.isEmpty() && verify==false){

                                 for (String value : articleid) {
                                     if (verify == false) {
                                         String nameRev = var;
                                         AppUser apuser = appUserRepository.findByEmail(nameRev)
                                                 .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                         String toadd = apuser.getIdArticlesCorrigString();
                                         AppUser chercheuruse = appUserRepository.findById(Long.parseLong(value))
                                                 .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                         String idarticle = value;

                                         if (chercheuruse.getLocation() != apuser.getLocation()) {

                                             if (toadd == "") {
                                                 toadd = idarticle;
                                             } else {
                                                 toadd = toadd + idarticle;
                                             }
                                             articleid.remove(idarticle);
                                             System.out.println("List toadd" + toadd);
                                             apuser.setIdArticlesCorrigString(toadd);
                                             appUserRepository.save(apuser);
                                             verify = true;

                                         } else {
                                             List.add(idarticle);
                                             articleid.remove(idarticle);
                                         }

                                     }
                                 }

                             }

                        } else {
                            if (!articleid.isEmpty()) {
                                for (String value : articleid) {
                                    if (verify == false) {
                                        String nameRev = var;
                                        AppUser apuser = appUserRepository.findByEmail(nameRev)
                                                .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                        String toadd = apuser.getIdArticlesCorrigString();
                                        AppUser chercheuruse = appUserRepository.findById(Long.parseLong(value))
                                                .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                        String idarticle = value;

                                        if (chercheuruse.getLocation() != apuser.getLocation()) {

                                            if (toadd == "") {
                                                toadd = idarticle;
                                            } else {
                                                toadd = toadd + idarticle;
                                            }
                                            articleid.remove(idarticle);
                                            System.out.println("List toadd" + toadd);
                                            apuser.setIdArticlesCorrigString(toadd);
                                            appUserRepository.save(apuser);
                                            verify = true;

                                        } else {
                                            List.add(idarticle);
                                            articleid.remove(idarticle);
                                        }

                                    }
                                }
                            }
                        }

                }

            } else {

                int AdivR = A / R;
                int AmodR = A % R;

                for (String var : revspec) {

                    AppUser apuser = appUserRepository.findByEmail(var)
                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                    String toadd = apuser.getIdArticlesCorrigString();

                    for (int i = 0; i < AdivR; i++) {

                        AppUser chercheuruse = appUserRepository.findById(Long.parseLong(articleid.get(i)))
                                .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));

                        String idarticle = articleid.get(i);
                        if (toadd == "") {
                            toadd = idarticle;
                        } else {
                            toadd = toadd + idarticle;
                        }
                        apuser.setIdArticlesCorrigString(toadd);
                        appUserRepository.save(apuser);

                    }
                    for (int i = 0; i < AdivR; i++) {
                        articleid.remove(i);
                    }

                }

                if (AmodR != 0) {

                    for (int i = 0; i < AmodR; i++) {
                        String nameRev = revspec.get(i);
                        String idarticle = articleid.get(i);
                        AppUser apuser = appUserRepository.findByEmail(nameRev)
                                .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                        String toadd = apuser.getIdArticlesCorrigString();
                        if (toadd == "") {
                            toadd = idarticle;
                        } else {
                            toadd = toadd + idarticle;
                        }
                        System.out.println("List toadd" + toadd);
                        apuser.setIdArticlesCorrigString(toadd);
                        appUserRepository.save(apuser);

                    }

                }

            }

        }
    }
}
