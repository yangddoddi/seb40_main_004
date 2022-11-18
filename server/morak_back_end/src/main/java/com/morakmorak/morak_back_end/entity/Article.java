package com.morakmorak.morak_back_end.entity;

import com.morakmorak.morak_back_end.entity.enums.ArticleStatus;
import com.morakmorak.morak_back_end.exception.BusinessLogicException;
import com.morakmorak.morak_back_end.exception.ErrorCode;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Builder.Default
    private Integer clicks = 0;

    private String title;

    private String content;

    @Builder.Default
    private Boolean isClosed = Boolean.FALSE;

    private Long thumbnail;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ArticleStatus articleStatus = ArticleStatus.POSTING;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder.Default
    @OneToMany(mappedBy = "article", cascade = CascadeType.PERSIST)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "article", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<ArticleTag> articleTags = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "vote_id")
    private Vote vote;

    @Builder.Default
    @OneToMany(mappedBy = "article", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<File> files = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "article")
    private List<Comment> comments = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "article")
    private List<Report> reports = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "article")
    private List<Review> reviews = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "article")
    private List<ArticleLike> articleLikes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "article")
    private List<Answer> answers = new ArrayList<>();


    public void injectUserForMapping(User user) {
        if (this.user != null) {
            this.user.getArticles().remove(this);
        }

        this.user = user;
        user.getArticles().add(this);
    }

    public void injectCategoryForMapping(Category category) {
        this.category = category;
        if (!category.getArticleList().contains(this)) {
            category.getArticleList().add(this);
        }
    }

    public void updateArticleElement(Article article) {
        Optional.ofNullable(article.getTitle()).ifPresent(presentTitle -> {
            this.title = presentTitle;
        });
        Optional.ofNullable(article.getContent()).ifPresent(presentContent -> {
            this.content = presentContent;
        });
        Optional.ofNullable(article.getThumbnail()).ifPresent(presentThumbnail -> {
            this.thumbnail = presentThumbnail;
        });
    }

    public void changeArticleStatus(ArticleStatus articleStatus) {
        this.articleStatus = articleStatus;
    }

    public void closeThisArticle() {
        this.isClosed = true;
    }

    public void addAnswer(Answer answer) {
        if (answer.getArticle() != null) throw new BusinessLogicException(ErrorCode.BAD_REQUEST);
        this.answers.add(answer);
        answer.isMappedWith(this);
    }
}
