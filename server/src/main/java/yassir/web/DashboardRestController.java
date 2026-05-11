package yassir.web;

import lombok.AllArgsConstructor;
import yassir.dtos.DashboardDTO;
import yassir.services.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DashboardRestController {
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public DashboardDTO dashboard() {
        return dashboardService.getDashboard();
    }
}
