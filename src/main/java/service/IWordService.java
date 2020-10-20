package service;

import model.Word;

import java.util.List;

public interface IWordService {
    List<Word> findAll();

    Word findExactWord(String keyword);

    List<Word> findWordsNearMeaning(String keyword);

    boolean deleteWordById(long id);

    boolean updateWord(Word word);

    boolean addNewWord(Word word);
}
