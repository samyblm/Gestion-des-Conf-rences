package com.example.demo.Articles;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> getArticle(Long id) {
        return articleRepository.findById(id);
    }

    public void addNewArticle(Article article,String Owner,Long idConf ) {
        article.setConference_id(idConf);
        article.setChercheur_id(Owner);
        articleRepository.save(article);
    }

    public void deleteArticle(Long articleId) {
        boolean exists = articleRepository.existsById(articleId);
        if (!exists){
            throw new IllegalStateException(
                    "article with id " + articleId + "does not exists"
            );
        }
        articleRepository.deleteById(articleId);
    }

    @Transactional
    public void updateArticle(Long articleId,
                              String name,
                              String titre,
                              String resumer,
                              String mots_cle,
                              String theme,
                              File file
                              ) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalStateException(
                "student with id " + articleId + "does not exists "
        ));

        if (name !=null && name.length() > 0 && !Objects.equals(article.getName(), name)){
            article.setName(name);
        }

        if (titre !=null && titre.length() > 0 && !Objects.equals(article.getTitre(), titre)){
            article.setName(titre);
        }

        if (theme !=null && theme.length() > 0 && !Objects.equals(article.getTheme(), theme)){
            article.setName(theme);

        }
        if (mots_cle !=null && mots_cle.length() > 0 && !Objects.equals(article.getMots_cle(), mots_cle)){
            article.setName(mots_cle);
        }

        if (resumer !=null && resumer.length() > 0 && !Objects.equals(article.getResumer(), resumer)){
            article.setResumer(resumer);
        }

        if (file !=null && file.getFileName().length() > 0 ){
            article.setFile(file);
        }



    }
}
