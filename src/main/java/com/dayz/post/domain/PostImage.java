package com.dayz.post.domain;

import com.dayz.common.entity.BaseEntity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post_image")
public class PostImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_image_id")
    private Long id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "sequence")
    private int sequence;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public static PostImage of(Long id, String fileName, int sequence) {
        Assert.notNull(fileName, "FileName must not be null.");

        PostImage postImage = new PostImage();
        postImage.setId(id);
        postImage.setFileName(fileName);
        postImage.setSequence(sequence);

        return postImage;
    }

    public static PostImage of(String fileName, int sequence) {
        Assert.notNull(fileName, "FileName must not be null.");

        PostImage postImage = new PostImage();
        postImage.setFileName(fileName);
        postImage.setSequence(sequence);

        return postImage;
    }

    public void changePost(Post post) {
        if (Objects.nonNull(this.post)) {
            post.getPostImages().remove(this);
        }
        post.getPostImages().add(this);
        this.post = post;
    }

}
