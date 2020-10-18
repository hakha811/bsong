package models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Song {
	private int id;
	private String name;
	private String preview_text;
	private String detail_text;
	private Timestamp date_create;
	private String picture;
	private int counter;
	private Category cat;
	public Song(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
}
