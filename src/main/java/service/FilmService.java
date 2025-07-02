package service;

import jakarta.transaction.Transactional;
import model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.FilmRepository;

import java.util.List;

@Service
@Transactional
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;

    public List<Film> getAllFilms() {
        List<Film> films = filmRepository.findAll();
        System.out.println("Films depuis le repository : " + films);
        return films;
    }

    public void saveFilm(Film film) {
        filmRepository.save(film);
    }

    public Film getFilmById(Long id) {
        return filmRepository.findById(id).orElse(null);
    }

    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }
}