package model;

public class Word {
    private long id;
    private String word;
    private String html;
    private String description;
    private String pronounce;

    public Word() {
    }

    public Word(long id, String word, String html, String description, String pronounce) {
        this.id = id;
        this.word = word;
        this.html = html;
        this.description = description;
        this.pronounce = pronounce;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    public void printMeaning() {
        String meaning = pronounce.equals("") ? word : word + " - " + pronounce;
        System.out.println(meaning);
        System.out.println(description);
    }
}