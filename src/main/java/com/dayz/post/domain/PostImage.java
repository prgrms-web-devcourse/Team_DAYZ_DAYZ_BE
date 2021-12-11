package com.dayz.post.domain;

import com.dayz.common.entity.BaseEntity;
import java.util.Objects;
import java.util.UUID;
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

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post_image")
public class PostImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "sequence")
    private int sequence;

    public static PostImage of(Long id, String imageUrl, int sequence) {
        PostImage postImage = new PostImage();
        postImage.setId(id);
        postImage.setImageUrl(imageUrl);
        postImage.setSequence(sequence);

        return postImage;
    }

    public static PostImage of(String imageUrl, int sequence) {
        PostImage postImage = new PostImage();
        postImage.setImageUrl(imageUrl);
        postImage.setSequence(sequence);

        return postImage;
    }

}
