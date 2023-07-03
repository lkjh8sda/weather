package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    // POST / create / diary
    @ApiOperation(value = "다이어리 생성", notes = "다이어리 생성")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestBody String text){
        diaryService.createDiary(date, text);
    }


    // GET / read / diary
    @ApiOperation(value = "다이어리 조회", notes = "다이어리 조회")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "날짜 형식 : yyyy-MM-dd") LocalDate date){
        return diaryService.readDiary(date);
    }

    // GET / read / diaries
    @ApiOperation(value = "다이어리 기간 조회", notes = "다이어리 기간 조회")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 기간 시작날", example = "2020-02-02") LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 기간 마지막날", example = "2020-02-02") LocalDate endDate){
        return diaryService.readDiaries(startDate,endDate);
    }

    // PUT / update / diary
    @ApiOperation(value = "다이어리 수정", notes = "다이어리 수정")
    @PutMapping("/update/diary")
    void updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                     @RequestBody String text){
        diaryService.updateDiary(date,text);
    }

    // DELETE / delete / diary
    @ApiOperation(value = "다이어리 삭제", notes = "다이어리 삭제")
    @DeleteMapping("/delete/diary")
    void deleteDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        diaryService.deleteDiary(date);
    }
}
