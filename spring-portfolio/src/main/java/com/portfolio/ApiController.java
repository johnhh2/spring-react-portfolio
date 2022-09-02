package com.example.springboot;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins="http://localhost:3000")
@RestController
public class ApiController {

    private class JSONResponse {

        private int key;

        public JSONResponse(int val) {
            this.key = val;
        }

        public int increment_by(int val) {
            this.key += val;
            return this.key;
        }

        public String toString() {
            return "{\"key\": " + this.key + ", \"foo\": \"bar\"}";
        }
    }

    public JSONResponse response = new JSONResponse(0);

	@RequestMapping(value="/api", method=RequestMethod.GET,
                    produces=MediaType.APPLICATION_JSON_VALUE)
	public String json() {
		return this.response.toString();
	}
    @RequestMapping(value="/api/edit", method=RequestMethod.POST)
    public int edit(@RequestBody int val) {
        return this.response.increment_by(val);
    }

}
