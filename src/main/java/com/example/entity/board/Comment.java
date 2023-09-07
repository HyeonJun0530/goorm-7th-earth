package com.example.entity.board;

import com.example.entity.BaseTimeEntity;
import com.example.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Comment")
@NoArgsConstructor
@Getter
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> child = new ArrayList<>();

    public static Comment createComment(Member member, Board board,
                                        String content, Comment parent) {
        Comment comment = new Comment();
        comment.member = member;
        comment.board = board;
        comment.content = content;
        comment.parent = parent;
        parent.addChildCategory(comment);

        return comment;
    }

    public static Comment createComment(Member member, Board board,
                                        String content) {
        Comment comment = new Comment();
        comment.member = member;
        comment.board = board;
        comment.content = content;

        return comment;
    }

    public void addChildCategory(Comment child) {
        this.child.add(child);
        child.setParent(this);
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }
}
