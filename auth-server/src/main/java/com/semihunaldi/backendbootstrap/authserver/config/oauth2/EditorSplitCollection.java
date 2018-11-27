package com.semihunaldi.backendbootstrap.authserver.config.oauth2;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import java.util.Collection;

/**
 * Created by semihunaldi on 21.11.2018
 */
public class EditorSplitCollection extends CustomCollectionEditor {

	private final Class<? extends Collection> collectionType;
	private final String splitRegex;

	public EditorSplitCollection(Class<? extends Collection> collectionType, String splitRegex) {
		super(collectionType, true);
		this.collectionType = collectionType;
		this.splitRegex = splitRegex;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(text == null || text.isEmpty()){
			super.setValue(super.createCollection(this.collectionType, 0));
		} else{
			super.setValue(text.split(splitRegex));
		}
	}
}