package configuration.properties.yaml;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "values")
@SuppressWarnings({"WeakerAccess", "unused"})
class YamlSettings {
    private Boolean enabled;

    private Message message;

    public static class Message {
        private String prefix;
        private String suffix;
        private String underwrite;

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }

        public String getUnderwrite() {
            return underwrite;
        }

        public void setUnderwrite(String underwrite) {
            this.underwrite = underwrite;
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
