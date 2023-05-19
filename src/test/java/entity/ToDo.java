package entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@JsonNaming(PropertyNamingStrategies.SNAKE_CASE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToDo {
//    "id": 13839,
//            "user_id": 1831902,
//            "title": "Confero virtus officiis vel soluta voluptatem.",
//            "due_on": "2023-06-10T00:00:00.000+05:30",
//            "status": "completed"
    private Long id;
    private Long userId;
    private String title;
    private String dueOn;
    private String status;
}
