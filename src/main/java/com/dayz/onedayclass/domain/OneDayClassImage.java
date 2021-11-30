package com.dayz.onedayclass.domain;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "onedayclass_iamges")
public class OneDayClassImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_uuid", nullable = false)
    private UUID image_Uuid;

    @Column(name = "sequence", nullable = false)
    private int sequence;

    @Column(name = "use_flag")
    private boolean use_flag;

    @ManyToOne
    @JoinColumn(name = "onedayclass_id")
    private OneDayClass oneDayClass;

}
