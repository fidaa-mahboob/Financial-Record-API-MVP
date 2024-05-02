terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "5.31.0"
    }
  }

  backend "s3" {
    bucket = "fidaa-terrafrom-state-bucket"
    key    = "dev/aws-infra"
    region = "eu-west-2"

    dynamodb_table = "terraform-locks"
    encrypt        = true
  }
}
provider "aws" {
  region     = var.aws_region
}