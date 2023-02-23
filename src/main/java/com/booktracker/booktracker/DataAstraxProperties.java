package com.booktracker.booktracker;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;

@ConfigurationProperties(prefix = "datastax.astra")
@Data
public class DataAstraxProperties {

    private File secureConnectBundle;
}
