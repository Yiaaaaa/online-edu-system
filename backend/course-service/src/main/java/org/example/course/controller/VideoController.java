@RestController
@RequestMapping("/courses/{courseId}/video")
public class VideoController {
    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<String> getVideoUrl(
            @PathVariable String courseId,
            @RequestHeader("Authorization") String token) {
        String studentId = authService.validateToken(token);
        if (!enrollmentService.isEnrolled(courseId, studentId)) {
            throw new RuntimeException("未购买本课程");
        }
        String videoUrl = courseService.getVideoUrl(courseId);
        return ResponseEntity.ok(videoUrl);
    }
}