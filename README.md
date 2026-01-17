# 🚀 Advanced DevOps CI/CD Project

[![CI Pipeline](https://github.com/YOUR_USERNAME/YOUR_REPO/actions/workflows/ci.yml/badge.svg)](https://github.com/YOUR_USERNAME/YOUR_REPO/actions/workflows/ci.yml)
[![CD Pipeline](https://github.com/YOUR_USERNAME/YOUR_REPO/actions/workflows/cd.yml/badge.svg)](https://github.com/YOUR_USERNAME/YOUR_REPO/actions/workflows/cd.yml)

## 📋 Project Overview

This project demonstrates a **production-grade CI/CD pipeline** using GitHub Actions, implementing DevSecOps principles with security scanning, quality gates, containerization, and Kubernetes deployment.

### Application Description
A simple Spring Boot REST API application that provides health check and version endpoints. The application serves as a demonstration platform for implementing comprehensive CI/CD practices.

**Tech Stack:**
- Java 17
- Spring Boot 3.2.1
- Maven
- Docker
- Kubernetes

---

## 🏗️ CI/CD Architecture

### CI Pipeline Stages

```
┌─────────────────────────────────────────────────────────────────┐
│                         CI PIPELINE                              │
├─────────────────────────────────────────────────────────────────┤
│  1. Code Quality (Checkstyle)                                   │
│  2. SAST - Static Security Testing (CodeQL)                     │
│  3. SCA - Dependency Security Scan (OWASP Dependency Check)     │
│  4. Unit Tests & Coverage (JUnit, JaCoCo)                       │
│  5. Build Application (Maven)                                   │
│  6. Docker Build & Image Scan (Trivy)                           │
│  7. Container Runtime Test                                      │
│  8. Push to DockerHub                                           │
└─────────────────────────────────────────────────────────────────┘
```

### CD Pipeline Stages

```
┌─────────────────────────────────────────────────────────────────┐
│                         CD PIPELINE                              │
├─────────────────────────────────────────────────────────────────┤
│  1. Deploy to Kubernetes                                        │
│  2. DAST - Dynamic Security Testing (OWASP ZAP)                 │
│  3. Smoke Tests                                                 │
│  4. Deployment Summary                                          │
└─────────────────────────────────────────────────────────────────┘
```

---

## 🔐 Security & Quality Controls

### DevSecOps Integration

| Stage | Tool | Purpose | Security Impact |
|-------|------|---------|----------------|
| **Linting** | Checkstyle | Code quality standards | Prevents technical debt |
| **SAST** | CodeQL | Static code analysis | Detects OWASP Top 10 vulnerabilities |
| **SCA** | OWASP Dependency Check | Dependency vulnerability scan | Identifies supply-chain risks |
| **Container Scan** | Trivy | Image vulnerability scan | Prevents vulnerable images |
| **DAST** | OWASP ZAP | Runtime security testing | Detects runtime vulnerabilities |

### Why Each Stage Matters

1. **Code Quality (Checkstyle)**
   - Enforces coding standards
   - Prevents technical debt accumulation
   - Improves code maintainability

2. **SAST (CodeQL)**
   - Detects security vulnerabilities in source code
   - Identifies OWASP Top 10 issues
   - Provides actionable remediation guidance

3. **SCA (OWASP Dependency Check)**
   - Scans third-party dependencies for known vulnerabilities
   - Prevents supply-chain attacks
   - Maintains CVE database awareness

4. **Unit Tests**
   - Validates business logic
   - Prevents regressions
   - Ensures code coverage targets

5. **Docker Build**
   - Creates reproducible container images
   - Multi-stage build reduces image size
   - Non-root user improves security

6. **Container Scanning (Trivy)**
   - Detects OS and library vulnerabilities
   - Prevents shipping vulnerable images
   - Integrates findings to GitHub Security tab

7. **Runtime Testing**
   - Validates container functionality
   - Ensures image is runnable
   - Tests application endpoints

8. **DAST (OWASP ZAP)**
   - Tests running application for vulnerabilities
   - Identifies runtime security issues
   - Complements SAST findings

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.6+
- Docker
- Kubernetes cluster (for CD)
- GitHub account with Actions enabled

### Local Development

1. **Clone the repository**
   ```bash
   git clone https://github.com/YOUR_USERNAME/YOUR_REPO.git
   cd YOUR_REPO
   ```

2. **Build the application**
   ```bash
   mvn clean package
   ```

3. **Run tests**
   ```bash
   mvn test
   ```

4. **Run locally**
   ```bash
   mvn spring-boot:run
   ```

5. **Test endpoints**
   ```bash
   curl http://localhost:8080/api/health
   curl http://localhost:8080/api/version
   ```

### Docker Build & Run

1. **Build Docker image**
   ```bash
   docker build -t devops-demo-app:latest .
   ```

2. **Run container**
   ```bash
   docker run -p 8080:8080 devops-demo-app:latest
   ```

3. **Test container**
   ```bash
   curl http://localhost:8080/api/health
   ```

---

## ⚙️ GitHub Secrets Configuration

Configure the following secrets in your GitHub repository:

| Secret Name | Description | Required |
|------------|-------------|----------|
| `DOCKERHUB_USERNAME` | DockerHub username | ✅ Yes |
| `DOCKERHUB_TOKEN` | DockerHub access token | ✅ Yes |
| `KUBECONFIG` | Base64 encoded kubeconfig | ✅ Yes (for CD) |

### Setting up secrets:

1. Go to **Settings → Secrets and variables → Actions**
2. Click **New repository secret**
3. Add each secret with its value

**For KUBECONFIG:**
```bash
cat ~/.kube/config | base64 | tr -d '\n'
```

---

## 📊 Pipeline Execution Flow

### CI Pipeline Triggers:
- Push to `main`/`master` branch
- Pull requests
- Manual workflow dispatch

### CD Pipeline Triggers:
- Successful completion of CI pipeline
- Manual workflow dispatch

### Pipeline Dependencies:
```
CI: code-quality → test → build → docker-build-scan → container-test → docker-push
CD: deploy-kubernetes → [dast-scan, smoke-tests] → deployment-summary
```

---

## 📁 Project Structure

```
DevOps/
├── .github/
│   └── workflows/
│       ├── ci.yml              # CI Pipeline
│       └── cd.yml              # CD Pipeline
├── .zap/
│   └── rules.tsv               # OWASP ZAP scanning rules
├── k8s/
│   ├── namespace.yaml          # Kubernetes namespace
│   ├── deployment.yaml         # Application deployment
│   └── service.yaml            # LoadBalancer service
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/devops/demo/
│   │   │       ├── DemoApplication.java
│   │   │       ├── controller/
│   │   │       │   └── HealthController.java
│   │   │       ├── service/
│   │   │       │   └── HealthService.java
│   │   │       └── model/
│   │   │           └── HealthResponse.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/devops/demo/
│               ├── DemoApplicationTests.java
│               ├── controller/
│               │   └── HealthControllerTest.java
│               └── service/
│                   └── HealthServiceTest.java
├── Dockerfile                  # Multi-stage Docker build
├── pom.xml                     # Maven configuration
├── .gitignore
├── .dockerignore
└── README.md
```

---

## 🎯 CI/CD Coverage Matrix

| Domain | Implementation | Status |
|--------|---------------|--------|
| **Continuous Integration** | Automated builds, tests | ✅ |
| **Code Quality** | Checkstyle | ✅ |
| **SAST** | CodeQL | ✅ |
| **SCA** | OWASP Dependency Check | ✅ |
| **Container Security** | Trivy | ✅ |
| **DAST** | OWASP ZAP | ✅ |
| **Containerization** | Docker multi-stage build | ✅ |
| **Orchestration** | Kubernetes deployment | ✅ |
| **Registry** | DockerHub push | ✅ |

---

## 🔍 Monitoring & Observability

### Application Endpoints

- **Health Check**: `http://localhost:8080/api/health`
- **Version**: `http://localhost:8080/api/version`
- **Actuator Health**: `http://localhost:8080/actuator/health`

### Kubernetes Monitoring

```bash
# Check pod status
kubectl get pods -n devops-demo

# Check service
kubectl get services -n devops-demo

# View logs
kubectl logs -f deployment/devops-demo-app -n devops-demo

# Check HPA status
kubectl get hpa -n devops-demo
```

---

## 🐛 Troubleshooting

### Common Issues

**1. Docker image not found**
- Ensure DockerHub secrets are configured correctly
- Verify image name matches in workflow

**2. Kubernetes deployment fails**
- Check KUBECONFIG secret is base64 encoded
- Verify cluster connectivity
- Check resource quotas

**3. Tests failing**
- Run `mvn clean test` locally
- Check test reports in `target/surefire-reports/`

**4. Security scan failures**
- Review Trivy/ZAP reports
- Update dependencies if vulnerabilities found
- Add suppressions if false positives

---

## 📈 Results & Observations

### Expected Outcomes:
1. ✅ All tests pass with >80% coverage
2. ✅ No critical security vulnerabilities
3. ✅ Docker image successfully pushed
4. ✅ Application deployed to Kubernetes
5. ✅ All endpoints responding correctly

### Performance Metrics:
- **CI Pipeline Duration**: ~8-12 minutes
- **CD Pipeline Duration**: ~5-8 minutes
- **Image Size**: ~200MB (after optimization)
- **Application Startup**: ~30 seconds

---

## 🔄 Limitations & Future Improvements

### Current Limitations:
1. DAST scan requires accessible endpoint
2. Limited to single environment deployment
3. No performance testing included

### Proposed Improvements:
1. **Multi-environment deployments** (dev, staging, prod)
2. **Performance testing** with JMeter/Gatling
3. **Infrastructure as Code** with Terraform
4. **Service mesh** integration (Istio)
5. **Advanced monitoring** (Prometheus, Grafana)
6. **GitOps** with ArgoCD
7. **Chaos engineering** tests

---

## 📝 Best Practices Implemented

✅ Shift-left security (early vulnerability detection)  
✅ Fail-fast pipelines (early error detection)  
✅ Immutable infrastructure (containers)  
✅ Infrastructure as Code (Kubernetes manifests)  
✅ Least privilege principle (non-root containers)  
✅ Secrets management (GitHub Secrets)  
✅ Multi-stage Docker builds (size optimization)  
✅ Health checks and readiness probes  
✅ Horizontal Pod Autoscaling  
✅ Comprehensive testing strategy  

---

## 👥 Team Information

**Project Members:**
- [Your Name] - [Student ID]
- [Team Member 2] - [Student ID] (if applicable)

**Submission Date:** January 18, 2026

---

## 📚 References

- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [OWASP Top 10](https://owasp.org/www-project-top-ten/)
- [Docker Security Best Practices](https://docs.docker.com/develop/security-best-practices/)
- [Kubernetes Documentation](https://kubernetes.io/docs/)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)

---

## 📄 License

This project is created for educational purposes as part of the DevOps assessment.

---

## 🤝 Contributing

This is an individual assessment project. Contributions are limited to team members only.

---

**Note:** Replace `YOUR_USERNAME` and `YOUR_REPO` with your actual GitHub username and repository name throughout this README and update the team information section.
