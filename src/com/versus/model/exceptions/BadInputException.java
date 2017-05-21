package com.versus.model.exceptions;

import java.io.IOException;

public class BadInputException extends IOException {

	public BadInputException(String message) {
		super(message);
	}
}
