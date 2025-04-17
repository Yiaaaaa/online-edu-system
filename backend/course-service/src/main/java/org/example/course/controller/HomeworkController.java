@RestController
@RequestMapping("/courses/{courseId}/homework")
public class HomeworkController {
    @Autowired
    private HomeworkService homeworkService;

    @PostMapping
    public ResponseEntity<?> submitHomework(
            @PathVariable String courseId,
            @RequestParam("file") MultipartFile file,
            @RequestHeader("Authorization") String token) {
        String studentId = authService.validateToken(token);

        // 校验截止时间
        if (homeworkService.isDeadlinePassed(courseId)) {
            throw new RuntimeException("已超过提交截止时间");
        }

        // 保存文件
        String filePath = homeworkService.saveHomeworkFile(file);
        homeworkService.submitHomework(studentId, courseId, filePath);

        return ResponseEntity.ok("作业提交成功");
    }

    @GetMapping
    public ResponseEntity<List<Homework>> getHomeworkStatus(
            @PathVariable String courseId,
            @RequestHeader("Authorization") String token) {
        String studentId = authService.validateToken(token);
        List<Homework> submissions = homeworkService.getSubmissions(studentId, courseId);
        return ResponseEntity.ok(submissions);
    }
}