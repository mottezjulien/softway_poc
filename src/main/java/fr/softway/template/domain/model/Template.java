package fr.softway.template.domain.model;

public class Template {

    private TemplateId id;
    private String code;
    private String frLabel;

    public Template(TemplateId id, String code, String frLabel) {
        this.id = id;
        this.code = code;
        this.frLabel = frLabel;
    }

    public Template(String code, String frLabel) {
        this(new TemplateId(), code, frLabel);
    }

    public String idValue() {
        return id.value();
    }

    public String code() {
        return code;
    }

    public String frLabel() {
        return frLabel;
    }

}
