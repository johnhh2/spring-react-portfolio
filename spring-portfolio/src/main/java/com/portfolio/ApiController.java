package com.example.springboot;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiController {

    private class JSONResponse {

        private int key;

        public JSONResponse(int val) {
            this.key = val;
        }

        public String toString() {
            return "{\"key\": " + this.key + ", \"foo\": \"bar\"}";
        }
    }

	@RequestMapping(value="/api", method=RequestMethod.GET,
                    produces=MediaType.APPLICATION_JSON_VALUE)
	public String index() {
		return new JSONResponse(0xdeadbeef).toString();
	}

}
