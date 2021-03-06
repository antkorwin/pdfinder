package com.antkorwin.pdfinder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created on 13/07/2020
 * <p>
 * Boundary to search text in selected area
 *
 * @author Korovin Anatoliy
 */
@Data
@Builder
@AllArgsConstructor
public class Boundary {

	private float x;
	private float y;
	private float width;
	private float height;


	//@formatter:off
	/*
	 * Check that point is in boundary.
	 * Axis orientation in PDF:
	 *
	 * y
	 * ^
	 * 890
	 * |     ______
	 * |    |  PDF|
	 * |    |     |
	 * |    |_____|
	 * |
	 * 0,0 ------------>  x  500
	 */
	public boolean isInBoundary(TextPosition position) {

		return  // -----------------------------------
				// |boundary    _________            |
				// |           ||text pos||          |
				// |           ||        ||          |
				// x          left       right    x+width
				// |           ||________||          |
				// |                                 |
				// -----------------------------------
				//
				position.getLeft() > this.x && position.getLeft() < this.x + width &&
				position.getRight() > this.x && position.getRight() < this.x + width &&
				//
				// -----------------------------------  y + height
				// |boundary    _________            |
				// |           ||text pos||top       |
				// |           ||        ||          |
				// |           ||        ||          |
				// |           ||________||bottom    |
				// |                                 |
				// -----------------------------------  y
				//
				position.getBottom() > this.y && position.getBottom() < this.y + height &&
				position.getTop() > this.y && position.getTop() < this.y + height;
	}
	//@formatter:on
}
