package org.liubov.statetaxcalculator.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class StateTaxBracket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter(value = AccessLevel.PRIVATE)
    private Integer id;
    @Column(name = "my_year", nullable = false)
    private Integer year;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String filingStatus;
    @Column(nullable = false)
    private Integer bracketLower;
    @Column(nullable = false)
    private Integer bracketUpper;
    @Column(nullable = false)
    private Double accumulatedAmount;
    @Column(nullable = false)
    private Double rate;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
}
