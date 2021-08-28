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

                for (String var : new ArrayList<>(revspec)) {
                    Boolean verify = false;

                    if (!List.isEmpty()) {

                        for (String value : new ArrayList<>(List)) {
                            if (verify == false) {
                                String nameRev = var;
                                AppUser apuser = appUserRepository.findByEmail(nameRev)
                                        .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                String toadd = apuser.getIdArticlesCorrigString();
                                Article chercheurusse = articleRepository.findById(Long.parseLong(value))
                                        .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                AppUser chercheuruse = appUserRepository.findByEmail(chercheurusse.getChercheur_id())
                                        .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                String idarticle = value;

                                if (chercheuruse.getLocation() != apuser.getLocation()) {

                                    if (toadd.equals("")) {
                                        toadd = idarticle;
                                    } else {
                                        toadd = toadd + idarticle;
                                    }
                                    List.remove(idarticle);
                                    System.out.println("List toadd" + toadd);
                                    String toaddtest = apuser.getTest();
                                    if (toaddtest.equals("")) {
                                        toaddtest = idarticle;
                                    } else {
                                        toaddtest = toaddtest+" " + idarticle;
                                    }
                                    System.out.println("AAAAAAA");
                                    apuser.setTest(toaddtest);
                                    apuser.setIdArticlesCorrigString(toadd);
                                    appUserRepository.save(apuser);
                                    verify = true;

                                }
                            }
                        }

                        if (!articleid.isEmpty() && verify == false) {

                            for (String value : new ArrayList<>(articleid)) {
                                if (verify == false) {
                                    String nameRev = var;
                                    AppUser apuser = appUserRepository.findByEmail(nameRev)
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    String toadd = apuser.getIdArticlesCorrigString();
                                    Article chercheurusse = articleRepository.findById(Long.parseLong(value))
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    AppUser chercheuruse = appUserRepository.findByEmail(chercheurusse.getChercheur_id())
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    String idarticle = value;

                                    if (chercheuruse.getLocation() != apuser.getLocation()) {

                                        if (toadd.equals("")) {
                                            toadd = idarticle;
                                        } else {
                                            toadd = toadd + idarticle;
                                        }
                                        articleid.remove(idarticle);
                                        String toaddtest = apuser.getTest();
                                        if (toaddtest.equals("")) {
                                            toaddtest = idarticle;
                                        } else {
                                            toaddtest = toaddtest+" " + idarticle;
                                        }
                                        apuser.setTest(toaddtest);
                                        System.out.println("BBBBBBBBBBBB");
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
                            for (String value : new ArrayList<>(articleid)) {
                                if (verify == false) {
                                    String nameRev = var;
                                    AppUser apuser = appUserRepository.findByEmail(nameRev)
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    String toadd = apuser.getIdArticlesCorrigString();
                                    Article chercheurusse = articleRepository.findById(Long.parseLong(value))
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    AppUser chercheuruse = appUserRepository.findByEmail(chercheurusse.getChercheur_id())
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    String idarticle = value;

                                    if (chercheuruse.getLocation() != apuser.getLocation()) {

                                        if (toadd.equals("")) {
                                            toadd = idarticle;
                                        } else {
                                            toadd = toadd+" " + idarticle;
                                        }
                                        articleid.remove(idarticle);
                                        String toaddtest = apuser.getTest();
                                        if (toaddtest.equals("")) {
                                            toaddtest = idarticle;
                                        } else {
                                            toaddtest = toaddtest +" "+ idarticle;
                                        }
                                        apuser.setTest(toaddtest);
                                        System.out.println("CCCCCCC");
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
                    int i = 0;
                    while (verify == false && articleid.isEmpty() && !revspec.get(i).equals(var)) {
                        System.out.println("test 1 boucle ");
                        System.out.println(i);
                        System.out.println(revspec.get(i));
                        System.out.println("test 1 end ");

                        String nameRev = var;
                        AppUser apuser = appUserRepository.findByEmail(nameRev)
                                .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                        AppUser apuser2 = appUserRepository.findByEmail(revspec.get(i))
                                .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                        Article chercheurusse2 = articleRepository.findById(Long.parseLong(apuser2.getTest()))
                                .orElseThrow(() -> new IllegalStateException("raihana tesssst"));
                        AppUser chercheuruse2 = appUserRepository.findByEmail(chercheurusse2.getChercheur_id())
                                .orElseThrow(() -> new IllegalStateException("raihana tesssst"));
                        System.out.println("test");
                        System.out.println(!apuser2.getTest().equals(""));
                        if (!apuser2.getTest().equals("")) {
                            System.out.println("location");
                            System.out.println(chercheuruse2.getLocation() != apuser.getLocation());
                            if (chercheuruse2.getLocation() != apuser.getLocation()) {
                                for (String variable : new ArrayList<>(List)) {
                                    Article chercheurusse3 = articleRepository.findById(Long.parseLong(variable))
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    AppUser chercheuruse3 = appUserRepository.findByEmail(chercheurusse3.getChercheur_id())
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    if (chercheuruse3.getLocation() != apuser2.getLocation() && verify == false) {
                                        String change = apuser2.getTest();
                                        apuser.setTest(change);
                                        String toaddtest = apuser.getGetIdArticlesCorrig();
                                        if (toaddtest.equals("")) {
                                            toaddtest = change;
                                        } else {
                                            toaddtest = toaddtest+ " " + change;
                                        }
                                        apuser.setIdArticlesCorrigString(toaddtest);
                                        appUserRepository.save(apuser);
                                        List.remove(variable);
                                        System.out.println("DDDDDDD");
                                        apuser2.setTest(variable);
                                        String teststring = apuser2.getIdArticlesCorrigString();
                                        String sttr = "";
                                        if (teststring.equals("")) {
                                            sttr = variable;
                                        } else {
                                            sttr = teststring.replaceAll(change, variable);

                                        }

                                        apuser2.setIdArticlesCorrigString(sttr);

                                        // apuser2.getIdArticlesCorrig().remove(change);
                                        appUserRepository.save(apuser2);
                                        verify = true;
                                    }

                                }

                            }

                        }
                        i++;

                    }

                }
            }

            else {

                int AdivR = A / R;
                int AmodR = A % R;

                for (String var : new ArrayList<>(revspec)) {
                    Boolean verify = false;
                    int cmp = 0;

                    if (!List.isEmpty()) {

                        for (String value : new ArrayList<>(List)) {
                            if (verify == false) {
                                String nameRev = var;
                                AppUser apuser = appUserRepository.findByEmail(nameRev)
                                        .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                String toadd = apuser.getIdArticlesCorrigString();
                                Article chercheurusse = articleRepository.findById(Long.parseLong(value))
                                        .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                AppUser chercheuruse = appUserRepository.findByEmail(chercheurusse.getChercheur_id())
                                        .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                String idarticle = value;

                                if (chercheuruse.getLocation() != apuser.getLocation()) {

                                    if (toadd.equals("")) {
                                        toadd = idarticle;
                                    } else {
                                        toadd =   toadd+ " " + idarticle;
                                    }
                                    List.remove(idarticle);
                                    cmp++;
                                    if (cmp == AdivR) {
                                        verify = true;
                                    }
                                    System.out.println("List toadd" + toadd);
                                    String toaddtest = apuser.getTest();
                                    if (toaddtest.equals("")) {
                                        toaddtest = idarticle;
                                    } else {
                                        toaddtest =  toaddtest +" "+ idarticle;
                                    }
                                    System.out.println("AAAAAAA");
                                    apuser.setTest(toaddtest);
                                    apuser.setIdArticlesCorrigString(toadd);
                                    appUserRepository.save(apuser);

                                }
                            }
                        }


                        if (!articleid.isEmpty() && verify == false) {

                            for (String value : new ArrayList<>(articleid)) {
                                if (verify == false) {
                                    String nameRev = var;
                                    AppUser apuser = appUserRepository.findByEmail(nameRev)
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    String toadd = apuser.getIdArticlesCorrigString();
                                    Article chercheurusse = articleRepository.findById(Long.parseLong(value))
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    AppUser chercheuruse = appUserRepository.findByEmail(chercheurusse.getChercheur_id())
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    String idarticle = value;

                                    if (chercheuruse.getLocation() != apuser.getLocation()) {

                                        if (toadd.equals("")) {
                                            toadd = idarticle;
                                        } else {
                                            toadd =  toadd+" "  + idarticle;
                                        }
                                        articleid.remove(idarticle);
                                        cmp++;
                                        if (cmp == AdivR) {
                                            verify = true;
                                        }
                                        String toaddtest = apuser.getTest();
                                        if (toaddtest.equals("")) {
                                            toaddtest = idarticle;
                                        } else {
                                            toaddtest =   toaddtest +" " + idarticle;
                                        }
                                        apuser.setTest(toaddtest);
                                        System.out.println("BBBBBBBBBBBB");
                                        System.out.println("List toadd" + toadd);
                                        apuser.setIdArticlesCorrigString(toadd);
                                        appUserRepository.save(apuser);

                                    } else {
                                        List.add(idarticle);
                                        articleid.remove(idarticle);
                                    }

                                }
                            }
                        }


                    } else {
                        if (!articleid.isEmpty()) {
                            for (String value : new ArrayList<>(articleid)) {
                                if (verify == false) {
                                    String nameRev = var;
                                    AppUser apuser = appUserRepository.findByEmail(nameRev)
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    String toadd = apuser.getIdArticlesCorrigString();
                                    Article chercheurusse = articleRepository.findById(Long.parseLong(value))
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    AppUser chercheuruse = appUserRepository.findByEmail(chercheurusse.getChercheur_id())
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    String idarticle = value;

                                    if (chercheuruse.getLocation() != apuser.getLocation()) {

                                        if (toadd.equals("")) {
                                            toadd = idarticle;
                                        } else {
                                            toadd = toadd +" " + idarticle;
                                        }
                                        articleid.remove(idarticle);
                                        cmp++;
                                        if (cmp == AdivR) {
                                            verify = true;
                                        }
                                        String toaddtest = apuser.getTest();
                                        if (toaddtest.equals("")) {
                                            toaddtest = idarticle;
                                        } else {
                                            toaddtest =  toaddtest +" " + idarticle;
                                        }
                                        apuser.setTest(toaddtest);
                                        System.out.println("CCCCCCC");
                                        System.out.println("List toadd" + toadd);
                                        apuser.setIdArticlesCorrigString(toadd);
                                        appUserRepository.save(apuser);


                                    } else {
                                        List.add(idarticle);
                                        articleid.remove(idarticle);
                                    }

                                }
                            }
                        }
                    }
                    int i = 0;
                    while (verify == false && articleid.isEmpty() && !revspec.get(i).equals(var)) {
                        System.out.println("test 1 boucle ");
                        System.out.println(i);
                        System.out.println(revspec.get(i));
                        System.out.println("test 1 end ");
                        String nameRev = var;
                        AppUser apuser = appUserRepository.findByEmail(nameRev)
                                .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                        AppUser apuser2 = appUserRepository.findByEmail(revspec.get(i))
                                .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));

                        for (String vartest : new ArrayList<>(apuser2.getTestarray())) {
                            if(verify==false) {

                                Article chercheuruses2 = articleRepository.findById(Long.parseLong(vartest))
                                        .orElseThrow(() -> new IllegalStateException("raihana tesssst"));
                                AppUser chercheuruse2 = appUserRepository.findByEmail(chercheuruses2.getChercheur_id())
                                        .orElseThrow(() -> new IllegalStateException("raihana tesssst"));
                                System.out.println("test");
                                System.out.println(!apuser2.getTest().equals(""));
                                if (!apuser2.getTest().equals("")) {
                                    System.out.println("location");
                                    System.out.println(chercheuruse2.getLocation() != apuser.getLocation());
                                    if (chercheuruse2.getLocation() != apuser.getLocation()) {
                                        for (String variable : new ArrayList<>(List)) {


                                            Article chercheuruse4 = articleRepository.findById(Long.parseLong(variable))
                                                    .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));

                                            AppUser chercheuruse3 = appUserRepository.findByEmail(chercheuruse4.getChercheur_id())
                                                    .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                            if (chercheuruse3.getLocation() != apuser2.getLocation() && verify == false) {
                                                String change = vartest;
                                                String toaddtestt = apuser.getTest();
                                                if (toaddtestt.equals("")) {
                                                    toaddtestt = change;
                                                } else {
                                                    toaddtestt = toaddtestt + " " + change;
                                                }
                                                apuser.setTest(toaddtestt);
                                                String toaddtest = apuser.getIdArticlesCorrigString();
                                                if (toaddtest.equals("")) {
                                                    toaddtest = change;
                                                } else {
                                                    toaddtest = toaddtest + " " + change;
                                                }
                                                apuser.setIdArticlesCorrigString(toaddtest);
                                                appUserRepository.save(apuser);
                                                List.remove(variable);
                                                System.out.println("DDDDDDD");
                                                apuser2.setTest(variable);
                                                String teststring = apuser2.getIdArticlesCorrigString();
                                                String sttr = "";
                                                if (teststring.equals("")) {
                                                    sttr = variable;
                                                } else {
                                                    sttr = teststring.replaceAll(change, variable);

                                                }

                                                apuser2.setIdArticlesCorrigString(sttr);

                                                // apuser2.getIdArticlesCorrig().remove(change);
                                                appUserRepository.save(apuser2);
                                                verify = true;
                                            }

                                        }

                                    }

                                }


                            }
                        }
                        i++;
                    }
                    // AppUser apuser = appUserRepository.findByEmail(var)
                    // .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                    // String toadd = apuser.getIdArticlesCorrigString();
                    //
                    // for (int i = 0; i < AdivR; i++) {
                    //
                    // AppUser chercheuruse =
                    // appUserRepository.findById(Long.parseLong(articleid.get(i)))
                    // .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                    //
                    // String idarticle = articleid.get(i);
                    // if (toadd == "") {
                    // toadd = idarticle;
                    // } else {
                    // toadd = toadd + idarticle;
                    // }
                    // apuser.setIdArticlesCorrigString(toadd);
                    // appUserRepository.save(apuser);
                    //
                    // }
                    // for (int i = 0; i < AdivR; i++) {
                    // articleid.remove(i);
                    // }

                }

                if(AmodR != 0){

                    for (String var : new ArrayList<>(revspec)) {
                        Boolean verifyy = false;

                        if (!List.isEmpty()) {

                            for (String valuee : new ArrayList<>(List)) {
                                if (verifyy == false) {
                                    String nameRev = var;
                                    AppUser apuser = appUserRepository.findByEmail(nameRev)
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    String toadd = apuser.getIdArticlesCorrigString();
                                    Article chercheurusse = articleRepository.findById(Long.parseLong(valuee))
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    AppUser chercheuruse = appUserRepository.findByEmail(chercheurusse.getChercheur_id())
                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                    String idarticle = valuee;

                                    if (chercheuruse.getLocation() != apuser.getLocation()) {

                                        if (toadd.equals("")) {
                                            toadd = idarticle;
                                        } else {
                                            toadd = toadd + " " + idarticle;
                                        }
                                        List.remove(idarticle);
                                        System.out.println("List toadd" + toadd);
                                        String toaddtest = apuser.getTest();
                                        if (toaddtest.equals("")) {
                                            toaddtest = idarticle;
                                        } else {
                                            toaddtest = toaddtest + " " + idarticle;
                                        }
                                        System.out.println("aaaaaaa");
                                        apuser.setTest(toaddtest);
                                        apuser.setIdArticlesCorrigString(toadd);
                                        appUserRepository.save(apuser);
                                        verifyy = true;

                                    }
                                }
                            }

                            if (!articleid.isEmpty() && verifyy == false) {

                                for (String value : new ArrayList<>(articleid)) {
                                    if (verifyy == false) {
                                        String nameRev = var;
                                        AppUser apuser = appUserRepository.findByEmail(nameRev)
                                                .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                        String toadd = apuser.getIdArticlesCorrigString();


                                        Article chercheurusse2 = articleRepository.findById(Long.parseLong(value))
                                                .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));


                                        AppUser chercheuruse2 = appUserRepository.findByEmail(chercheurusse2.getChercheur_id())
                                                .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                        String idarticle = value;

                                        if (chercheuruse2.getLocation() != apuser.getLocation()) {

                                            if (toadd.equals("")) {
                                                toadd = idarticle;
                                            } else {
                                                toadd = toadd + " " + idarticle;
                                            }
                                            articleid.remove(idarticle);
                                            String toaddtest = apuser.getTest();
                                            if (toaddtest.equals("")) {
                                                toaddtest = idarticle;
                                            } else {
                                                toaddtest = toaddtest + " " + idarticle;
                                            }
                                            apuser.setTest(toaddtest);
                                            System.out.println("BBBBBBBBBBBB");
                                            System.out.println("List toadd" + toadd);
                                            apuser.setIdArticlesCorrigString(toadd);
                                            appUserRepository.save(apuser);
                                            verifyy = true;

                                        } else {
                                            List.add(idarticle);
                                            articleid.remove(idarticle);
                                        }

                                    }
                                }

                            }

                        } else {
                            if (!articleid.isEmpty()) {
                                System.out.println("articleeeeeeeeeee id 648");
                                System.out.println(articleid);
                                System.out.println("/");
                                System.out.println(var);
                                System.out.println("Article 648 end");
                                for (String value1 : new ArrayList<>(articleid)) {
                                    if (verifyy == false) {
                                        String nameRev = var;
                                        AppUser apuser = appUserRepository.findByEmail(nameRev)
                                                .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                        String toadd = apuser.getIdArticlesCorrigString();
                                        System.out.println("!!!!!hna samy error nchfo idarticle");
                                        System.out.println(nameRev);
                                        System.out.println("/");
                                        System.out.println(value1);
                                        System.out.println("end!!");
                                        Long value11 = Long.parseLong(value1);

                                        Article chercheuruse1 = articleRepository.findById(value11)
                                                .orElseThrow(() -> new IllegalStateException("samy yexestish"));

                                        AppUser chercheuruse = appUserRepository.findByEmail(chercheuruse1.getChercheur_id())
                                                .orElseThrow(() -> new IllegalStateException("samy yexestish"));
                                        String idarticle = value1;

                                        if (chercheuruse.getLocation() != apuser.getLocation()) {

                                            if (toadd.equals("")) {
                                                toadd = idarticle;
                                            } else {
                                                toadd = toadd + " " + idarticle;
                                            }
                                            articleid.remove(idarticle);
                                            String toaddtest = apuser.getTest();
                                            if (toaddtest.equals("")) {
                                                toaddtest = idarticle;
                                            } else {
                                                toaddtest = toaddtest + " " + idarticle;
                                            }
                                            apuser.setTest(toaddtest);
                                            System.out.println("CCCCCCC");
                                            System.out.println("List toadd" + toadd);
                                            apuser.setIdArticlesCorrigString(toadd);
                                            appUserRepository.save(apuser);
                                            verifyy = true;

                                        } else {
                                            List.add(idarticle);
                                            articleid.remove(idarticle);
                                        }

                                    }
                                }
                            }
                        }
                        int i = 0;
                        System.out.println("!revspec.get(i).toString().equals(var.toString()");
                        System.out.println(!revspec.get(i).toString().equals(var.toString()));
                        System.out.println((!revspec.get(i).toString().equals(var.toString()) || !var.equals(revspec.get(i))));
                        System.out.println(!revspec.get(i).equals(var));
                        System.out.println(revspec.get(i));
                        System.out.println(var);
                        System.out.println(verifyy);
                        System.out.println(i);
                        while (verifyy == false && articleid.isEmpty() && (!revspec.get(i).toString().equals(var.toString()) || !var.equals(revspec.get(i)))) {
                            System.out.println("test 1 boucle ");
                            System.out.println(i);
                            System.out.println(revspec.get(i));
                            System.out.println("test 1 end ");
                            System.out.println(i);
                            System.out.println("test 111 end ");
                            String nameRev = var;
                            AppUser apuser = appUserRepository.findByEmail(nameRev)
                                    .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                            AppUser apuser2 = appUserRepository.findByEmail(revspec.get(i))
                                    .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));

                                    for (String vartest : new ArrayList<>(apuser2.getTestarray())) {
                                        if(verifyy==false) {
                                            Article chercheuruses2 = articleRepository.findById(Long.parseLong(vartest))
                                                    .orElseThrow(() -> new IllegalStateException("raihana tesssst"));
                                        AppUser chercheuruse2 = appUserRepository.findByEmail(chercheuruses2.getChercheur_id())
                                                .orElseThrow(() -> new IllegalStateException("raihana tesssst"));
                                        System.out.println("test");
                                        System.out.println(!apuser2.getTest().equals(""));
                                        if (!apuser2.getTest().equals("")) {
                                            System.out.println("location");
                                            System.out.println(chercheuruse2.getLocation() != apuser.getLocation());
                                            if (chercheuruse2.getLocation() != apuser.getLocation()) {
                                                for (String variable : new ArrayList<>(List)) {


                                                    Article chercheuruse4 = articleRepository.findById(Long.parseLong(variable))
                                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));

                                                    AppUser chercheuruse3 = appUserRepository.findByEmail(chercheuruse4.getChercheur_id())
                                                            .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
                                                    if (chercheuruse3.getLocation() != apuser2.getLocation() && verifyy == false) {
                                                        String change = vartest;
                                                        String toaddtestt = apuser.getTest();
                                                        if (toaddtestt.equals("")) {
                                                            toaddtestt = change;
                                                        } else {
                                                            toaddtestt = toaddtestt + " " + change;
                                                        }
                                                        apuser.setTest(toaddtestt);
                                                        String toaddtest = apuser.getIdArticlesCorrigString();
                                                        if (toaddtest.equals("")) {
                                                            toaddtest = change;
                                                        } else {
                                                            toaddtest = toaddtest + " " + change;
                                                        }
                                                        apuser.setIdArticlesCorrigString(toaddtest);
                                                        appUserRepository.save(apuser);
                                                        List.remove(variable);
                                                        System.out.println("DDDDDDD");
                                                        apuser2.setTest(variable);
                                                        String teststring = apuser2.getIdArticlesCorrigString();
                                                        String sttr = "";
                                                        if (teststring.equals("")) {
                                                            sttr = variable;
                                                        } else {
                                                            sttr = teststring.replaceAll(change, variable);

                                                        }

                                                        apuser2.setIdArticlesCorrigString(sttr);

                                                        // apuser2.getIdArticlesCorrig().remove(change);
                                                        appUserRepository.save(apuser2);

                                                        verifyy = true;
                                                    }

                                                }

                                            }

                                        }


                                    }
                                }
                            i++;
                    }

                    }

                }


            }



            // if (AmodR != 0) {
            //
            // for (int i = 0; i < AmodR; i++) {
            // String nameRev = revspec.get(i);
            // String idarticle = articleid.get(i);
            // AppUser apuser = appUserRepository.findByEmail(nameRev)
            // .orElseThrow(() -> new IllegalStateException("reviewer yexestish"));
            // String toadd = apuser.getIdArticlesCorrigString();
            // if (toadd == "") {
            // toadd = idarticle;
            // } else {
            // toadd = toadd + idarticle;
            // }
            // System.out.println("List toadd" + toadd);
            // apuser.setIdArticlesCorrigString(toadd);
            // appUserRepository.save(apuser);
            //
            // }
            //
            // }
            //
            // }

        }
    }
}
