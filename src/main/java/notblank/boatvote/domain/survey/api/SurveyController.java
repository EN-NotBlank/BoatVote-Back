package notblank.boatvote.domain.survey.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import notblank.boatvote.domain.survey.dto.request.CreateSurveyRequest;
import notblank.boatvote.domain.survey.dto.response.SurveyResponse;
import notblank.boatvote.domain.survey.service.SurveyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/survey")
@Tag(name = "Survey API", description = "설문 추가, 설문 단건 조회, 참여가능 설문 조회")
public class SurveyController {
    private final SurveyService surveyService;

    @GetMapping("/{surveyId}")
    @Operation(summary = "설문 단건 조회")
    public SurveyResponse getSurveyById(@PathVariable int surveyId){
        return surveyService.getSurveyResponseById(surveyId);
    }

    @PostMapping()
    @Operation(summary = "설문 생성")
    public int createSurvey(@RequestBody CreateSurveyRequest createSurveyRequest){
        return surveyService.addNewSurvey(createSurveyRequest);
    }

    @GetMapping("/available/{uid}")
    @Operation(summary = "특정 유저가 참여가능한 설문 조회")
    public List<SurveyResponse> getAvailableSurveys(@PathVariable int uid){
        return surveyService.getAvailableSurveys(uid);
    }

    @GetMapping("/requested/{uid}")
    @Operation(summary = "특정 유저가 의뢰한 설문 조회")
    public List<SurveyResponse> getRequestedSurveys(@PathVariable("uid") int uid) {
        return surveyService.getRequestedSurveys(uid);
    }
}