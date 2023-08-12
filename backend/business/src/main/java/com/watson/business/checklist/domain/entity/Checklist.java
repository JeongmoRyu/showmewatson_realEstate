package com.watson.business.checklist.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "checklists")
public class Checklist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "house_id")
	private Long houseId;

	@Column(name = "user_id")
	private String userId;

	@ElementCollection
	@CollectionTable(name = "checklist_items")
	@Column(name = "check_list_item")
	private List<Integer> checkListItem = new ArrayList<>();
}
