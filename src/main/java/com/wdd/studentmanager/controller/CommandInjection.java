package com.wdd.studentmanager.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CommandInjection {
    public static class CommandResult {
        private String output;
        private String error;
        // getters
        public String getOutput() {
            return output;
        }
        public String getError() {
            return error;
        }
        // setters
        public void setOutput(String output) {
            this.output = output;
        }
        public void setError(String error) {
            this.error = error;
        }

    }

//    @PostMapping(value = "/execute", consumes = MediaType.TEXT_PLAIN_VALUE)
//    public CommandResult runCommand(@RequestBody String command) { //存在漏洞版本
//        String output = "";
//        String error = "";
//        // 执行指定的字符串命令
//        try {
//            Process p = Runtime.getRuntime().exec(command);
//            p.waitFor();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                output += line + "\n";
//            }
//            BufferedReader readerError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
//            while ((line = readerError.readLine()) != null) {
//                error += line + "\n";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        CommandResult result = new CommandResult();
//        result.setOutput(output);
//        result.setError(error);
//
//        return result;
//    }
    @PostMapping(value = "/execute", consumes = MediaType.TEXT_PLAIN_VALUE)
    public CommandResult runCommand(@RequestBody String command) {
        String output = "";
        String error = "";

        // 执行指定的字符串命令
        try {
            List<String> commandWithArgs = new ArrayList<String>();
            commandWithArgs.add(command); // your command
            // you can add arguments here if needed
            // commandWithArgs.add("myArg");

            ProcessBuilder processBuilder = new ProcessBuilder(commandWithArgs);
            Process p = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output += line + "\n";
            }

            BufferedReader readerError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((line = readerError.readLine()) != null) {
                error += line + "\n";
            }

            p.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
        CommandResult result = new CommandResult();
        result.setOutput(output);
        result.setError(error);

        return result;
    }
}