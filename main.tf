provider "kubernetes" {
  config_path = "~/.kube/config"
  config_context_cluster = "kind-kind"
}

resource "null_resource" "kind_cluster" {
  triggers = {
    kubernetes_resources = sha256(join(",", [
      filesha256("sky-journey-auth/sky-journey-auth-deployment.yaml"),
      filesha256("sky-journey-auth/sky-journey-auth-service.yaml"),
      filesha256("sky-journey-core/sky-journey-core-deployment.yaml"),
      filesha256("sky-journey-core/sky-journey-core-service.yaml"),
      filesha256("sky-journey-db/sky-journey-db-deployment.yaml"),
      filesha256("sky-journey-db/sky-journey-db-service.yaml"),
      filesha256("sky-journey-dbadmin/sky-journey-dbadmin-deployment.yaml"),
      filesha256("sky-journey-dbadmin/sky-journey-dbadmin-service.yaml"),
      filesha256("sky-journey-payment/sky-journey-payment-deployment.yaml"),
      filesha256("sky-journey-payment/sky-journey-payment-service.yaml"),
      filesha256("sky-journey-portainer/sky-journey-portainer-deployment.yaml"),
      filesha256("sky-journey-portainer/sky-journey-portainer-service.yaml"),
      filesha256("sky-journey-portainer/portainer-sa.yaml"),
      filesha256("sky-journey-portainer/portainer-rbac.yaml"),
      filesha256("sky-journey-portainer/portainer-cluster.yaml"),
      filesha256("sky-journey-ui/sky-journey-ui-deployment.yaml"),
      filesha256("sky-journey-ui/sky-journey-ui-service.yaml"),
      "https://downloads.portainer.io/ce2-19/portainer-agent-k8s-nodeport.yaml"
    ]))
  }

  provisioner "local-exec" {
    command = "kind create cluster --config k8s-config/cluster-config.yaml"
  }

  provisioner "local-exec" {
    command = "kubectl apply -f sky-journey-db/sky-journey-db-deployment.yaml"
  }

  provisioner "local-exec" {
    command = "kubectl apply -f sky-journey-db/sky-journey-db-service.yaml"
  }

  provisioner "local-exec" {
    command = "kubectl apply -f sky-journey-core/sky-journey-core-deployment.yaml"
  }

  provisioner "local-exec" {
    command = "kubectl apply -f sky-journey-core/sky-journey-core-service.yaml"
  }

  provisioner "local-exec" {
    command = "kubectl apply -f sky-journey-payment/sky-journey-payment-deployment.yaml"
  }

  provisioner "local-exec" {
    command = "kubectl apply -f sky-journey-payment/sky-journey-payment-service.yaml"
  }

  provisioner "local-exec" {
    command = "kubectl apply -f sky-journey-auth/sky-journey-auth-deployment.yaml"
  }

  provisioner "local-exec" {
    command = "kubectl apply -f sky-journey-auth/sky-journey-auth-service.yaml"
  }

  provisioner "local-exec" {
    command = "kubectl apply -f sky-journey-ui/sky-journey-ui-deployment.yaml"
  }

  provisioner "local-exec" {
    command = "kubectl apply -f sky-journey-ui/sky-journey-ui-service.yaml"
  }

  provisioner "local-exec" {
    command = "kubectl apply -f sky-journey-dbadmin/sky-journey-dbadmin-deployment.yaml"
  }

  provisioner "local-exec" {
    command = "kubectl apply -f sky-journey-dbadmin/sky-journey-dbadmin-service.yaml"
  }

  provisioner "local-exec" {
    command = "kubectl apply -f sky-journey-portainer/portainer-sa.yaml"
  }

  provisioner "local-exec" {
    command = "kubectl apply -f sky-journey-portainer/portainer-cluster.yaml"
  }

  provisioner "local-exec" {
    command = "kubectl apply -f sky-journey-portainer/portainer-rbac.yaml"
  }

  provisioner "local-exec" {
    command = "kubectl apply -f sky-journey-portainer/sky-journey-portainer-deployment.yaml"
  }

  provisioner "local-exec" {
    command = "kubectl apply -f sky-journey-portainer/sky-journey-portainer-service.yaml"
  }

  provisioner "local-exec" {
    command = "kubectl apply -f https://downloads.portainer.io/ce2-19/portainer-agent-k8s-nodeport.yaml"
  }

  provisioner "local-exec" {
    when    = destroy
    command = "kind delete cluster"
  }
}
