package service;

import dao.IWordDAO;
import dao.WordDAO;
import model.Word;

import java.util.List;

public class WordService implements IWordService {

    private IWordDAO wordDAO;

    public WordService() {
        wordDAO = new WordDAO();
    }

    @Override
    public List<Word> findAll() {
        return wordDAO.getAll();
    }

    @Override
    public Word findExactWord(String keyword) {
        return wordDAO.findExactWord(keyword.toUpperCase());
    }

    @Override
    public List<Word> findWordsNearMeaning(String keyword) {
        keyword = String.join("%", keyword.split("")) + "%";
        return wordDAO.findWordsNearMeaning(keyword);
    }

    @Override
    public boolean deleteWordById(long id) {
        return wordDAO.deleteWordById(id);
    }

    @Override
    public boolean updateWord(Word word) {
        return wordDAO.updateWord(word);
    }

    @Override
    public boolean addNewWord(Word word) {
        return wordDAO.addNewWord(word);
    }
}
