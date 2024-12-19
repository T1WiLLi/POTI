package poti.project.server.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards", uniqueConstraints = {
                @UniqueConstraint(columnNames = { "name" })
}, indexes = {
                @Index(name = "idx_cards_rarity", columnList = "rarity"),
                @Index(name = "idx_cards_energy_cost", columnList = "energy_cost"),
                @Index(name = "idx_cards_hp", columnList = "hp")
})
public class Card {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 100)
        private String name;

        @Column
        private String description;

        @Column(nullable = false, length = 20)
        private String rarity;

        @Column(nullable = false)
        private Integer energyCost;

        @Column(nullable = false)
        private Integer hp;

        @Column(nullable = false)
        private Integer damage;

        @Column(nullable = false, length = 255)
        private String imageUrl;

        @CreationTimestamp
        @Column(nullable = false)
        private LocalDateTime createdAt;
}
